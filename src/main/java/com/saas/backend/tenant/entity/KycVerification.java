package com.saas.backend.tenant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(
        name = "kyc_verifications",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_kyc_tenant",
                        columnNames = "tenant_id"
                )
        }
)
@NoArgsConstructor
@AllArgsConstructor
public class KycVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "tenant_id",
            nullable = false,
            unique = true
    )
    private Tenant tenant;

    private String panNumber;

    /*
     * Prefer storing a provider/reference ID
     * instead of storing raw Aadhaar information.
     */
    private String aadhaarReference;

    private String businessName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private KycStatus status;

    private LocalDateTime submittedAt;

    private LocalDateTime verifiedAt;

    private String rejectionReason;

    @PrePersist
    protected void onCreate() {

        if (status == null) {
            status = KycStatus.PENDING;
        }
    }

}
