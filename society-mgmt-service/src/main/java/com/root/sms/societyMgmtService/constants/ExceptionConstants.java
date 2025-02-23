package com.root.sms.societyMgmtService.constants;

public enum ExceptionConstants {

    INTERNAL_ERROR("Something went Wrong"),
    SERVICE_DOWN("Our Services are down. Pleas try again later after some time"),
    DATA_NOT_FOUND("Requested Data not available"),
    RESOURCE_NOT_FOUND("Resource not found"),
    FILE_UPLOAD_FAILED("File Upload Failed"),
    UNAUTHORISED("User not authorised."),
    INVALID_EMAIL("Please check email id provided"),
    INVALID_REQUEST("Request is invalid.");
    public final String description;
    private ExceptionConstants(String description) {
        this.description = description;
    }
}
