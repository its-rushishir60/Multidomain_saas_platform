package com.saas.backend.onboarding.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OnboardingResponse {
    private Long tenantId;

    private Integer currentStep;

    private Integer progressPercentage;

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


}
