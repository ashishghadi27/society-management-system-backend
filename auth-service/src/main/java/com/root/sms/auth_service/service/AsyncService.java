package com.root.sms.auth_service.service;

import com.root.sms.auth_service.constants.LoggingConstants;
import com.root.sms.auth_service.helpers.SendEmailHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AsyncService {

    private final SendEmailHelper emailHelper;

    @Async
    public void sendEmail(String otp, String emailId) {
        String methodName = "sendEmail";
        try{
            log.info(LoggingConstants.LOG_INFO_FORMAT, methodName, "Sending email to: ", emailId);
            emailHelper.sendMail(otp, emailId);
            log.info(LoggingConstants.LOG_INFO_FORMAT, methodName, "Email Sent to: ", emailId);
        }
        catch (Exception e){
            log.error(LoggingConstants.LOG_ERROR_FORMAT, methodName,
                    e.getCause(), e.getMessage(), "SENDING EMAIL FAILED");
            Thread.currentThread().interrupt();
        }
    }
}
