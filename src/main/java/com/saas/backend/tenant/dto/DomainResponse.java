package com.saas.backend.tenant.dto;

import com.saas.backend.tenant.entity.DomainStatus;
import com.saas.backend.tenant.entity.DomainType;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class DomainResponse {
    private Long id;
    private Long tenantId;
    private String domain;
    private DomainType type;
    private DomainStatus status;
    private boolean primaryDomain;


}
