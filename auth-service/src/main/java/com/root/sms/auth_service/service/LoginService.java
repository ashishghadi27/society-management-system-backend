package com.root.sms.auth_service.service;


import com.root.commondependencies.exception.ValidationException;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.vo.*;

public interface LoginService {

    RegisterResponseVO register(MemberVO requestVO) throws  GlobalException;
    AuthResponseVO login(AuthRequestVO request) throws GlobalException;
    OtpResponseVO forgotPassword(AuthRequestVO request) throws ValidationException, GlobalException;
    OtpResponseVO validateOtp(OtpRequestVO otpRequest) throws GlobalException, ValidationException;

}
