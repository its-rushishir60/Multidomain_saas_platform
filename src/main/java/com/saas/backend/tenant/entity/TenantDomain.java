package com.saas.backend.tenant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant_domains",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_domain",
                        columnNames = "domain"
                )
        }
        )
public class TenantDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false, unique = true)
    private String domain;

    @Enumerated(EnumType.STRING)
    private DomainType type;

    @Enumerated(EnumType.STRING)
    private DomainStatus status;

    private boolean primaryDomain;

    private LocalDateTime verifiedAt;

    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {

        createdAt = LocalDateTime.now();

        if (status == null) {
            status = DomainStatus.PENDING;
        }
    }
}
