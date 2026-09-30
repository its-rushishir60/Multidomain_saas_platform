package com.saas.backend.tenant.entity;

import com.saas.backend.address.entity.Address;
import com.saas.backend.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenants",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_tenant_slug",
                        columnNames = "slug"
                ),
                @UniqueConstraint(
                        name = "uk_tenant_email",
                        columnNames = "email"
                )
        }
        )
public class Tenant extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    private String businessName;

    private String businessType;

    @Enumerated(EnumType.STRING)
    private TenantStatus status;

    private String logoUrl;

    private LocalDateTime launchedAt;





}
