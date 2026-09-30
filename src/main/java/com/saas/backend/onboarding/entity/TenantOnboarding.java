package com.saas.backend.onboarding.entity;

import com.saas.backend.tenant.entity.Tenant;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tenant_onboarding",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_onboarding_tenant",
                        columnNames = "tenant_id"
                )
        })
public class TenantOnboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false, unique = true)
    private Tenant tenant;

    private boolean profileCompleted;

    private boolean domainCompleted;

    private boolean kycCompleted;

    private boolean logoCompleted;

    private boolean themeCompleted;

    private boolean customizationCompleted;

    private boolean policiesCompleted;

    private boolean aboutUsCompleted;

    private boolean productsCompleted;

    private boolean paymentGatewayCompleted;

    private boolean launchCompleted;

    private Integer currentStep;

    private Integer progressPercentage;

    @PrePersist
    protected void onCreate() {

        if (currentStep == null) {
            currentStep = 1;
        }

        if (progressPercentage == null) {
            progressPercentage = 0;
        }
    }
}
