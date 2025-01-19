package com.root.sms.auth_service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthRequestVO {

    private String emailId;
    private String password;

}
