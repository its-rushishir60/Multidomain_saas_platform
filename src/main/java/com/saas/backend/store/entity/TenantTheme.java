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
@Table(name = "tenant_themes")
public class TenantTheme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "theme_id", nullable = false)
//    private Theme theme;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Theme theme;

    @Column(columnDefinition = "TEXT")
    private String customConfig;

    private Boolean active;
}
