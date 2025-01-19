package com.root.sms.api_gateway.constants;

public enum ExceptionConstants {

    INTERNAL_ERROR("Something went Wrong"),
    SERVICE_DOWN("Our Services are down. Pleas try again later after some time"),
    DATA_NOT_FOUND("Requested Data not available"),
    UNAUTHORISED("User not authorised."),
    INVALID_EMAIL("Please check email id provided"),
    INVALID_REQUEST("Request is invalid.");
    public final String description;
    private ExceptionConstants(String description) {
        this.description = description;
    }
}
