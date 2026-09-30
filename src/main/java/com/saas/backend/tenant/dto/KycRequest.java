package com.saas.backend.tenant.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KycRequest {
    private Long tenantId;
    private String panNumber;
    private String aadhaarReference;
    private String businessName;

}
