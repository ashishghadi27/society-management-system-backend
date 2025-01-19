package com.root.sms.api_gateway.exception;

import com.root.sms.api_gateway.constants.ExceptionConstants;
import com.root.sms.api_gateway.utils.CommonUtil;
import com.root.sms.api_gateway.vo.ErrorVO;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.DefaultErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;

import java.util.Map;

@Component
public class GlobalErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(ServerRequest request, ErrorAttributeOptions options) {
        Exception ex = (Exception) getError(request);

        ErrorVO error = new ErrorVO();
        if (ex instanceof GlobalException globalException) {
            error.setErrorCode(String.valueOf(globalException.getErrorCode()));
            error.setErrorMsg(globalException.getErrorMessage());
            error.setDescription(globalException.getDescription());
        } else {
            error.setErrorCode(HttpStatus.BAD_REQUEST.toString());
            error.setErrorMsg(ExceptionConstants.INTERNAL_ERROR.name());
        }

        return CommonUtil.getMapFromObject(error);
    }
}