package com.root.sms.societyMgmtService.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "societies")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Society {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sid", nullable = false)
    private Long sid;

    @Column(name = "name")
    private String name;

    @Column(name = "address_line1")
    private String addressLine1;

    @Column(name = "address_line2")
    private String addressLine2;

    @Column(name = "plot_number")
    private String plotNumber;

    @Column(name = "profile_pic")
    private String profilePic;

    @Column(name = "society_fund")
    private String societyFund;

    @Column(name = "parking_available")
    private Boolean parkingAvailable;

    @Column(name = "is_approved")
    private Boolean isApproved = Boolean.FALSE;

    @Column(name = "created_at", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
