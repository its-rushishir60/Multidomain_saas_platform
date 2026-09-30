package com.saas.backend.onboarding.service;

import com.saas.backend.onboarding.dto.OnboardingResponse;
import com.saas.backend.onboarding.entity.TenantOnboarding;
import com.saas.backend.onboarding.repository.TenantOnboardingRepository;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.repository.TenantRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OnboardingService {

    private final TenantOnboardingRepository onboardingRepository;
    private final TenantRepository tenantRepository;

    public OnboardingService(
            TenantOnboardingRepository onboardingRepository,
            TenantRepository tenantRepository
    ) {
        this.onboardingRepository = onboardingRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public OnboardingResponse initialize(Long tenantId) {

        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        TenantOnboarding onboarding =
                onboardingRepository
                        .findByTenantId(tenantId)
                        .orElseGet(() -> {

                            TenantOnboarding newOnboarding =
                                    new TenantOnboarding();

                            newOnboarding.setTenant(tenant);

                            return onboardingRepository.save(
                                    newOnboarding
                            );
                        });

        return mapToResponse(onboarding);
    }

    public OnboardingResponse getProgress(Long tenantId) {

        TenantOnboarding onboarding =
                onboardingRepository.findByTenantId(tenantId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Onboarding not found"
                                ));

        return mapToResponse(onboarding);
    }

    @Transactional
    public OnboardingResponse completeStep(
            Long tenantId,
            Integer step
    ) {

        TenantOnboarding onboarding =
                onboardingRepository.findByTenantId(tenantId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Onboarding not found"
                                ));

        switch (step) {

            case 1 -> onboarding.setProfileCompleted(true);

            case 2 -> onboarding.setDomainCompleted(true);

            case 3 -> onboarding.setKycCompleted(true);

            case 4 -> onboarding.setLogoCompleted(true);

            case 5 -> onboarding.setThemeCompleted(true);

            case 6 -> onboarding.setCustomizationCompleted(true);

            case 7 -> onboarding.setPoliciesCompleted(true);

            case 8 -> onboarding.setAboutUsCompleted(true);

            case 9 -> onboarding.setProductsCompleted(true);

            case 10 ->
                    onboarding.setPaymentGatewayCompleted(true);

            case 11 ->
                    onboarding.setLaunchCompleted(true);

            default ->
                    throw new IllegalArgumentException(
                            "Step must be between 1 and 11"
                    );
        }

        calculateProgress(onboarding);

        return mapToResponse(
                onboardingRepository.save(onboarding)
        );
    }

    private void calculateProgress(
            TenantOnboarding onboarding
    ) {

        int completed = 0;

        if (onboarding.isProfileCompleted()) completed++;
        if (onboarding.isDomainCompleted()) completed++;
        if (onboarding.isKycCompleted()) completed++;
        if (onboarding.isLogoCompleted()) completed++;
        if (onboarding.isThemeCompleted()) completed++;
        if (onboarding.isCustomizationCompleted()) completed++;
        if (onboarding.isPoliciesCompleted()) completed++;
        if (onboarding.isAboutUsCompleted()) completed++;
        if (onboarding.isProductsCompleted()) completed++;
        if (onboarding.isPaymentGatewayCompleted()) completed++;
        if (onboarding.isLaunchCompleted()) completed++;

        int percentage =
                Math.round((completed / 11.0f) * 100);

        onboarding.setProgressPercentage(percentage);

        int nextStep = 11;

        if (!onboarding.isProfileCompleted()) {
            nextStep = 1;
        } else if (!onboarding.isDomainCompleted()) {
            nextStep = 2;
        } else if (!onboarding.isKycCompleted()) {
            nextStep = 3;
        } else if (!onboarding.isLogoCompleted()) {
            nextStep = 4;
        } else if (!onboarding.isThemeCompleted()) {
            nextStep = 5;
        } else if (!onboarding.isCustomizationCompleted()) {
            nextStep = 6;
        } else if (!onboarding.isPoliciesCompleted()) {
            nextStep = 7;
        } else if (!onboarding.isAboutUsCompleted()) {
            nextStep = 8;
        } else if (!onboarding.isProductsCompleted()) {
            nextStep = 9;
        } else if (!onboarding.isPaymentGatewayCompleted()) {
            nextStep = 10;
        } else if (!onboarding.isLaunchCompleted()) {
            nextStep = 11;
        }

        onboarding.setCurrentStep(nextStep);
    }

    private OnboardingResponse mapToResponse(
            TenantOnboarding onboarding
    ) {

        OnboardingResponse response =
                new OnboardingResponse();

        response.setTenantId(
                onboarding.getTenant().getId()
        );

        response.setCurrentStep(
                onboarding.getCurrentStep()
        );

        response.setProgressPercentage(
                onboarding.getProgressPercentage()
        );

        response.setProfileCompleted(
                onboarding.isProfileCompleted()
        );

        response.setDomainCompleted(
                onboarding.isDomainCompleted()
        );

        response.setKycCompleted(
                onboarding.isKycCompleted()
        );

        response.setLogoCompleted(
                onboarding.isLogoCompleted()
        );

        response.setThemeCompleted(
                onboarding.isThemeCompleted()
        );

        response.setCustomizationCompleted(
                onboarding.isCustomizationCompleted()
        );

        response.setPoliciesCompleted(
                onboarding.isPoliciesCompleted()
        );

        response.setAboutUsCompleted(
                onboarding.isAboutUsCompleted()
        );

        response.setProductsCompleted(
                onboarding.isProductsCompleted()
        );

        response.setPaymentGatewayCompleted(
                onboarding.isPaymentGatewayCompleted()
        );

        response.setLaunchCompleted(
                onboarding.isLaunchCompleted()
        );

        return response;
    }
}
