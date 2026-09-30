package com.saas.backend.tenant.service;

import com.saas.backend.tenant.dto.DomainRequest;
import com.saas.backend.tenant.dto.DomainResponse;
import com.saas.backend.tenant.entity.DomainStatus;
import com.saas.backend.tenant.entity.DomainType;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.entity.TenantDomain;
import com.saas.backend.tenant.repository.TenantDomainRepository;
import com.saas.backend.tenant.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DomainService {

    @Autowired
    TenantDomainRepository tenantDomainRepository;

    @Autowired
    TenantRepository tenantRepository;

    public DomainService(
            TenantDomainRepository tenantDomainRepository,
            TenantRepository tenantRepository
    ){
        this.tenantDomainRepository = tenantDomainRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public DomainResponse addDomain(DomainRequest request) {

        if (tenantDomainRepository.existsByDomain(request.getDomain())) {
            throw new RuntimeException("Domain already registered");
        }

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        TenantDomain domain = new TenantDomain();

        domain.setTenant(tenant);
        domain.setDomain(request.getDomain().toLowerCase().trim());

        domain.setType(
                request.getDomain().contains(".")
                        ? DomainType.CUSTOM
                        : DomainType.SUBDOMAIN
        );

        domain.setStatus(DomainStatus.PENDING);

        boolean hasPrimaryDomain =
                tenantDomainRepository
                        .findByTenantIdAndPrimaryDomainTrue(
                                tenant.getId()
                        )
                        .isPresent();

        domain.setPrimaryDomain(!hasPrimaryDomain);

        TenantDomain saved = tenantDomainRepository.save(domain);

        return mapToResponse(saved);
    }

    public List<DomainResponse> getTenantDomains(Long tenantId) {

        return tenantDomainRepository.findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private DomainResponse mapToResponse(TenantDomain domain) {

        return new DomainResponse(
                domain.getId(),
                domain.getTenant().getId(),
                domain.getDomain(),
                domain.getType(),
                domain.getStatus(),
                domain.isPrimaryDomain()
        );
    }


}
