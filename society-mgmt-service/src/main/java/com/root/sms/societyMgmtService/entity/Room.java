package com.root.sms.societyMgmtService.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(name = "room_no")
    private String roomNo;

    @Column(name = "room_size")
    private String roomSize;

    @ManyToOne
    @JoinColumn(name = "sid", nullable = false)
    @JsonIgnore
    private Society society;

    @OneToOne(mappedBy = "room", cascade = CascadeType.ALL)
    @JsonBackReference
    private Member member;
}
