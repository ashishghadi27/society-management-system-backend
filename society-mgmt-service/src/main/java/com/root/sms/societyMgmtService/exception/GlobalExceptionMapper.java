package com.root.sms.societyMgmtService.exception;

import com.root.commondependencies.exception.ValidationException;
import com.root.sms.societyMgmtService.constants.ExceptionConstants;
import com.root.sms.societyMgmtService.vo.ErrorVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVO> handleException(
            Exception ex, WebRequest request) {
        ErrorVO error = new ErrorVO();
        if(ex instanceof GlobalException globalException){
            error.setErrorCode(String.valueOf(globalException.getErrorCode()));
            error.setErrorMsg(globalException.getErrorMessage());
            error.setDescription(globalException.getDescription());
            return new ResponseEntity<ErrorVO>(error, HttpStatus.BAD_REQUEST);
        }
        else if(ex instanceof ValidationException validationException){
            error.setErrorCode(String.valueOf(validationException.getErrorCode()));
            error.setErrorMsg(validationException.getErrorMessage());
            return new ResponseEntity<ErrorVO>(error, HttpStatus.BAD_REQUEST);
        }
        error.setErrorCode(HttpStatus.BAD_REQUEST.toString());
        error.setErrorMsg(ExceptionConstants.INTERNAL_ERROR.name());
        error.setDescription(ExceptionConstants.INTERNAL_ERROR.description);
        return new ResponseEntity<ErrorVO>(error, HttpStatus.BAD_REQUEST);
    }

}
