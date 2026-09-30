package com.saas.backend.tenant.service;

import com.saas.backend.tenant.dto.KycRequest;
import com.saas.backend.tenant.dto.KycResponse;
import com.saas.backend.tenant.entity.KycStatus;
import com.saas.backend.tenant.entity.KycVerification;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.repository.KycRepository;
import com.saas.backend.tenant.repository.TenantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public KycService(
            KycRepository kycRepository,
            TenantRepository tenantRepository
    ) {
        this.kycRepository = kycRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public KycResponse submitKyc(KycRequest request) {

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        KycVerification kyc =
                kycRepository.findByTenantId(tenant.getId())
                        .orElse(new KycVerification());

        kyc.setTenant(tenant);
        kyc.setPanNumber(request.getPanNumber());
        kyc.setAadhaarReference(request.getAadhaarReference());
        kyc.setBusinessName(request.getBusinessName());
        kyc.setStatus(KycStatus.SUBMITTED);
        kyc.setSubmittedAt(LocalDateTime.now());

        KycVerification saved = kycRepository.save(kyc);

        return mapToResponse(saved);
    }

    public KycResponse getKyc(Long tenantId) {

        KycVerification kyc =
                kycRepository.findByTenantId(tenantId)
                        .orElseThrow(() ->
                                new RuntimeException("KYC not found"));

        return mapToResponse(kyc);
    }

    @Transactional
    public KycResponse verifyKyc(Long tenantId) {

        KycVerification kyc =
                kycRepository.findByTenantId(tenantId)
                        .orElseThrow(() ->
                                new RuntimeException("KYC not found"));

        kyc.setStatus(KycStatus.VERIFIED);
        kyc.setVerifiedAt(LocalDateTime.now());

        return mapToResponse(
                kycRepository.save(kyc)
        );
    }

    private KycResponse mapToResponse(
            KycVerification kyc
    ) {

        return new KycResponse(
                kyc.getId(),
                kyc.getTenant().getId(),
                kyc.getBusinessName(),
                kyc.getStatus()
        );
    }
}
