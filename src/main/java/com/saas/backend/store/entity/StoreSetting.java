package com.saas.backend.store.entity;

import com.saas.backend.tenant.entity.Tenant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "store_settings")
public class StoreSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, unique = true)
    private Tenant tenant;

    private String storeName;

    private String storeDescription;

    private String logoUrl;

    private String faviconUrl;

    private String currency;

    private String Tagline;

    private String contactEmail;

    private String contactPhone;

    private String facebookUrl;

    private String instagramUrl;

    private String youtubeUrl;

    private Boolean maintenanceMode;
}