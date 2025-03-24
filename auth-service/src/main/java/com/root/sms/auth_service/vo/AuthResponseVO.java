package com.root.sms.auth_service.vo;

import com.root.sms.auth_service.entity.Room;
import com.root.sms.auth_service.entity.Society;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthResponseVO {

    private boolean validUser;
    private boolean sessionId;
    private String refreshToken;
    private MemberVO user;
    private Society society;
    private Room room;

}
