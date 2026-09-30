package com.saas.backend.tenant.dto;

import com.saas.backend.address.entity.Address;
import com.saas.backend.tenant.entity.TenantStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class TenantResponse {
    private Long id;
    private String name;
    private String slug;
    private String email;
    private String phone;
    private Address address;
    private String businessName;
    private String businessType;
    private TenantStatus status;

    public TenantResponse() {
    }

    public TenantResponse(
            Long id,
            String name,
            String slug,
            String email,
            String phone,
            Address  address,
            String businessName,
            String businessType,
            TenantStatus status
    ) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.businessName = businessName;
        this.businessType = businessType;
        this.status = status;
    }

}
