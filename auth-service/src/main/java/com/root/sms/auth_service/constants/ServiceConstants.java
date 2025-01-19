package com.root.sms.auth_service.constants;

public enum ServiceConstants {
    SEND_OTP_SUCCESS("Successfully sent OTP to your registered email id");

    public final String description;
    private ServiceConstants(String description) {
        this.description = description;
    }
}
