package com.saas.backend.tenant.service;

import com.saas.backend.tenant.dto.TenantRequest;
import com.saas.backend.tenant.dto.TenantResponse;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.entity.TenantStatus;
import com.saas.backend.tenant.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TenantService {

    @Autowired
    private TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public TenantResponse createTenant(TenantRequest request) {

        if (tenantRepository.existsBySlug(request.getSlug())) {
            throw new RuntimeException("Tenant slug already exists");
        }

        if (tenantRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Tenant email already exists");
        }

        Tenant tenant = new Tenant();

        tenant.setName(request.getName());
        tenant.setSlug(request.getSlug());
        tenant.setEmail(request.getEmail());
        tenant.setPhone(request.getPhone());
        tenant.setAddress(request.getAddress());
        tenant.setBusinessName(request.getBusinessName());
        tenant.setBusinessType(request.getBusinessType());

        tenant.setStatus(TenantStatus.ONBOARDING);

        Tenant savedTenant = tenantRepository.save(tenant);

        return mapToResponse(savedTenant);
    }

    public TenantResponse getTenant(Long id) {

        Tenant tenant = tenantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        return mapToResponse(tenant);
    }

    private TenantResponse mapToResponse(Tenant tenant) {

        return new TenantResponse(
                tenant.getId(),
                tenant.getName(),
                tenant.getSlug(),
                tenant.getEmail(),
                tenant.getPhone(),
                tenant.getAddress(),
                tenant.getBusinessName(),
                tenant.getBusinessType(),
                tenant.getStatus()
        );
    }
}
