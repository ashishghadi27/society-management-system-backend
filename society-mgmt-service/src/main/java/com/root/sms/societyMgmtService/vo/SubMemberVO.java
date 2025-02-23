package com.root.sms.societyMgmtService.vo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubMemberVO {
    private Long memberId;
    private String firstName;
    private String lastName;
    private String type;
}
