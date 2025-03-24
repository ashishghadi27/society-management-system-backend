package com.root.sms.auth_service.impl;

import com.root.commondependencies.exception.ValidationException;
import com.root.redis.services.RedisContextWrapper;
import com.root.sms.auth_service.config.ConsulConfig;
import com.root.sms.auth_service.constants.ExceptionConstants;
import com.root.sms.auth_service.constants.LoggingConstants;
import com.root.sms.auth_service.context.SocietyContext;
import com.root.sms.auth_service.entity.Room;
import com.root.sms.auth_service.entity.Society;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.helpers.CookieHelper;
import com.root.sms.auth_service.helpers.DBHelper;
import com.root.sms.auth_service.helpers.SessionHelper;
import com.root.sms.auth_service.repo.RoomRepository;
import com.root.sms.auth_service.repo.SocietyRepository;
import com.root.sms.auth_service.service.AsyncService;
import com.root.sms.auth_service.service.LoginService;
import com.root.sms.auth_service.utils.CommonUtil;
import com.root.sms.auth_service.utils.Constants;
import com.root.sms.auth_service.utils.ValidationUtil;
import com.root.sms.auth_service.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final SessionHelper sessionHelper;

    private final CookieHelper cookieHelper;

    private final DBHelper dbHelper;

    private final AsyncService asyncService;
    private final RoomRepository roomRepository;
    private final SocietyRepository societyRepository;

    private final RedisContextWrapper redisContextWrapper;

    private final ConsulConfig config;

    @Override
    public RegisterResponseVO register(MemberVO requestVO) throws GlobalException  {
        String methodName = "register";
        String sessionId = sessionHelper.getSessionId();
        log.info(LoggingConstants.LOG_INFO_FORMAT, methodName, "Session Id", sessionId);

        RegisterResponseVO registerResponseVO = new RegisterResponseVO();
        try {
            Long mid = dbHelper.registerMember(requestVO);
            registerResponseVO.setRegisterUserSuccessful(mid != null);
        } catch (GlobalException e) {
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), e.getDescription());
            throw e;
        }
        catch (Exception e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
                    .description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
        return registerResponseVO;
    }

    @Override
    public AuthResponseVO login(AuthRequestVO request) throws GlobalException {
        String methodName = "login";
        AuthResponseVO authResponse = new AuthResponseVO();
        String sessionId = sessionHelper.getSessionId();
        log.info(LoggingConstants.LOG_INFO_FORMAT, methodName, "Session Id", sessionId);
        try {
            ValidationUtil.validateRequest(request);
            MemberVO memberVO = dbHelper.getMemberByEmail(request.getEmailId());
            if (ValidationUtil.isValidUser(request.getPassword(), memberVO.getHashedPassword())) {
                authResponse.setValidUser(true);
                memberVO.setHashedPassword(null);

                SocietyContext context = new SocietyContext();
                context.setMemberVO(memberVO);
                redisContextWrapper.setContext(sessionId, context);

                authResponse.setUser(memberVO);

                Optional<Room> roomOptional = roomRepository.findByRid(memberVO.getRoomId());
                if(roomOptional.isPresent()){
                    Room room = roomOptional.get();
                    Optional<Society> societyOptional = societyRepository.findBySidAndIsApprovedTrue(room.getSocietyId());
                    if (societyOptional.isPresent()){
                        authResponse.setRoom(room);
                        authResponse.setSociety(societyOptional.get());
                    }
                }

                cookieHelper.setCookie(memberVO);
            }
        } catch (GlobalException e) {
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), e.getDescription());
            throw e;
        }
        catch (Exception e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
                    .description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
        return authResponse;
    }

    @Override
    public OtpResponseVO forgotPassword(AuthRequestVO request) throws GlobalException {
        String methodName = "login";
        OtpResponseVO otpResponseVO = new OtpResponseVO();
        String sessionId = sessionHelper.getSessionId();
        log.info(LoggingConstants.LOG_INFO_FORMAT, methodName, "Session Id", sessionId);
        try {
            ValidationUtil.validateEmail(request.getEmailId());
            MemberVO memberVO = dbHelper.getMemberByEmail(request.getEmailId());
            memberVO.setHashedPassword(null);
            if (StringUtils.isNotEmpty(memberVO.getEmail())) {
                String otp = CommonUtil.generateOtp();

                SocietyContext context = new SocietyContext();
                context.setOtp(otp);
                context.setMemberVO(memberVO);
                context.setOtpSentTime(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

                redisContextWrapper.setContext(sessionId, context);
                cookieHelper.setCookie();
                otpResponseVO.setResponseCode("200");
                otpResponseVO.setResponseMsg("SEND_OTP_SUCCESS");

                CompletableFuture.runAsync(() -> asyncService.sendEmail(otp, request.getEmailId()));

                return otpResponseVO;
            }
        } catch (GlobalException e) {
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), e.getDescription());
            throw e;
        }
        catch (Exception e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "");
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INTERNAL_ERROR.name())
                    .description(ExceptionConstants.INTERNAL_ERROR.description).build();
        }
        return otpResponseVO;
    }

    @Override
    public OtpResponseVO validateOtp(OtpRequestVO otpRequest) throws GlobalException, ValidationException {
        String sessionId = sessionHelper.getSessionId();

        SocietyContext context = redisContextWrapper.getContext(sessionId, SocietyContext.class);
        ValidationUtil.validateContext(context);

        String generatedOtp = context.getOtp();


        OtpResponseVO otpResponseVO = new OtpResponseVO();
        if (StringUtils.isNotEmpty(generatedOtp)
                && generatedOtp.equalsIgnoreCase(otpRequest.getOtp())) {
            LocalDateTime currentDateTime = LocalDateTime.now();
            LocalDateTime otpSentTime = LocalDateTime.parse(context.getOtpSentTime(),
                    DateTimeFormatter.ISO_DATE_TIME);
            int otpTimeOut = config.getConfigValueByKey("OTP_TIMEOUT_IN_MINS", Constants.OTP_TIMEOUT_IN_MINS);
            otpSentTime = otpSentTime.plusMinutes(otpTimeOut);
            if ((currentDateTime.isBefore(otpSentTime) || currentDateTime.equals(otpSentTime))) {
                cookieHelper.setCookie(context.getMemberVO());

                context.setOtp(null);
                redisContextWrapper.setContext(sessionId, context);

                otpResponseVO.setResponseMsg("VERIFY_OTP_SUCCESS");
                return otpResponseVO;
            }
            throw new ValidationException.Builder().errorMessage("OTP_EXPIRED").build();
        }
        throw new ValidationException.Builder().errorMessage("VERIFY_OTP_FAILED").build();
    }
}
