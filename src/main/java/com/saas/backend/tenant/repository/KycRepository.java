package com.saas.backend.tenant.repository;

import com.saas.backend.tenant.entity.KycVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KycRepository
        extends JpaRepository<KycVerification, Long> {

    Optional<KycVerification> findByTenantId(Long tenantId);

    boolean existsByTenantId(Long tenantId);
}
