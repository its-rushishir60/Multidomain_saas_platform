package com.saas.backend.tenant.dto;

import com.saas.backend.tenant.entity.KycStatus;
import lombok.Data;

@Data
public class KycResponse {
    private Long id;
    private Long tenantId;
    private String businessName;
    private KycStatus status;

    public KycResponse(
            Long id,
            Long tenantId,
            String businessName,
            KycStatus status
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.businessName = businessName;
        this.status = status;
    }
}
