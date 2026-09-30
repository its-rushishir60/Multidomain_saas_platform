package com.saas.backend.tenant.repository;

import com.saas.backend.tenant.entity.TenantDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TenantDomainRepository extends JpaRepository<TenantDomain,Long> {

    List<TenantDomain> findByTenantId(Long tenantId);

    Optional<TenantDomain> findByDomain(String domain);

    boolean existsByDomain(String domain);

    Optional<TenantDomain> findByTenantIdAndPrimaryDomainTrue(Long tenatId);
}
