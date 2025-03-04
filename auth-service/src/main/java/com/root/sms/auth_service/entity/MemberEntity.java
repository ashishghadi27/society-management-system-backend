package com.root.sms.auth_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@Data
public class MemberEntity {

    @Id
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "hashed_password")
    private String hashedPassword;

    @Column(name = "type")
    private String type;

    @Column(name = "rid")
    private Long roomId;

    @Column(name = "approved")
    private Boolean approved;

    @CreationTimestamp
    @Column(name = "created_At", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_At", columnDefinition = "TIMESTAMP")
    private LocalDateTime updatedAt;

}
