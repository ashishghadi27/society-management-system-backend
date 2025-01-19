package com.root.sms.api_gateway.exception;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
public class GlobalException extends Exception{

    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    public static final String SERVICE_DOWN = "SERVICE_DOWN";

    public static final String DATA_NOT_FOUND = "DATA_NOT_FOUND";

    public static final String UNAUTHORISED = "UNAUTHORISED";
    private Integer errorCode;
    private String errorMessage;
    private String description;

    public GlobalException(Builder builder){
        super(builder.errorMessage);
        this.errorCode = builder.errorCode;
        this.errorMessage = builder.errorMessage;
        this.description = builder.description;
    }

    public static class Builder {

        private Integer errorCode;
        private String errorMessage;
        private String description;

        public GlobalException build(){
            if(this.errorCode == null){
                this.errorCode = HttpStatus.BAD_REQUEST.value();
            }
            return new GlobalException(this);
        }

        public Builder errorCode(Integer errorCode){
            this.errorCode = errorCode;
            return this;
        }

        public Builder errorMessage(String errorMessage){
            this.errorMessage = errorMessage;
            return this;
        }

        public Builder description(String url){
            this.description = url;
            return this;
        }

    }

}
