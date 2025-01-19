package com.root.sms.auth_service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseVO {

    private boolean validUser;
    private boolean sessionId;
    private String refreshToken;
    private MemberVO user;

}
