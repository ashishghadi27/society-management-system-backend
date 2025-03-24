package com.root.sms.auth_service.vo;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomVO {
    private Long societyId;
    private String roomNo;
    private String roomSize;
}
