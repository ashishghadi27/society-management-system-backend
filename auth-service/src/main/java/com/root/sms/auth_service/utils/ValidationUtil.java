package com.root.sms.auth_service.utils;

import com.root.sms.auth_service.constants.ExceptionConstants;
import com.root.sms.auth_service.context.SocietyContext;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.vo.AuthRequestVO;
import com.root.sms.auth_service.vo.MemberVO;
import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.root.redis.constants.ExceptionConstants.INVALID_REQUEST;

public final class ValidationUtil {

    private static final String emailRegex = "[a-zA-Z0-9][a-zA-Z0-9_.]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+";

    public static void validateRequest(AuthRequestVO requestVO) throws GlobalException {
        validateEmail(requestVO.getEmailId());
        if (StringUtils.isEmpty(requestVO.getPassword())) {
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INVALID_REQUEST.name())
                    .description(ExceptionConstants.INVALID_REQUEST.description).build();
        }
    }

    public static boolean isValidUser(String requestPassword, String actualPassword) {
        return requestPassword.equals(actualPassword);
    }


    public static void validateEmail(String email) throws GlobalException {
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.find()) {
            throw new GlobalException.Builder()
                    .errorMessage(ExceptionConstants.INVALID_EMAIL.name())
                    .description(ExceptionConstants.INVALID_EMAIL.description).build();
        }
    }

    public static void validateRegisterUser(MemberVO memberVO, MemberVO requestVO) throws GlobalException {
        if (requestVO.getEmail().equals(memberVO.getEmail())) {
            throw new GlobalException.Builder().errorMessage("Email is already exists!.").build();
        }
    }

    public static void validateContext(SocietyContext context) throws GlobalException {
        if (context == null) {
            throw new GlobalException.Builder().errorMessage(INVALID_REQUEST).build();
        }
    }
}
