package com.root.sms.auth_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rid")
    private Long rid;

    @Column(name = "sid")
    private Long societyId;

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "room_size")
    private String roomSize;
}
