package com.root.sms.auth_service.controllers;


import com.root.commondependencies.exception.ValidationException;
import com.root.sms.auth_service.exception.GlobalException;
import com.root.sms.auth_service.service.LoginService;
import com.root.sms.auth_service.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/login")
    public AuthResponseVO login(@RequestBody AuthRequestVO requestVO) throws GlobalException {
        return loginService.login(requestVO);
    }

    @PostMapping("/register")
    public RegisterResponseVO register(MemberVO memberVO) throws GlobalException {
        return loginService.register(memberVO);
    }

    @PostMapping("/forgot-password")
    public OtpResponseVO forgotPassword(AuthRequestVO request) throws ValidationException, GlobalException {
        return loginService.forgotPassword(request);
    }

    @PostMapping("/validate-otp")
    public OtpResponseVO validateOtp(OtpRequestVO otpRequest) throws GlobalException, ValidationException{
        return loginService.validateOtp(otpRequest);
    }

}
