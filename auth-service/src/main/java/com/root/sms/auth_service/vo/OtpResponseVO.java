package com.root.sms.auth_service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OtpResponseVO {

    private String responseCode;
    private String responseMsg;

}
