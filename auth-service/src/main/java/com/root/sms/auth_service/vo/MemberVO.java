package com.root.sms.auth_service.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberVO {

    private String firstName;
    private String lastName;
    private String email;
    private String hashedPassword;
    private String type;
    private Long roomId;

}
