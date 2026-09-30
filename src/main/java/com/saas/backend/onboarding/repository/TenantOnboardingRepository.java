package com.saas.backend.onboarding.repository;

import com.saas.backend.onboarding.entity.TenantOnboarding;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TenantOnboardingRepository extends JpaRepository<TenantOnboarding, Long> {

    Optional<TenantOnboarding> findByTenantId(Long tenantId);

    boolean existsByTenantId(Long tenantId);
}
