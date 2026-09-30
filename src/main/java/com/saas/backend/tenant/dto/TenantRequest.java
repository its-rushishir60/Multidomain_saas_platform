package com.saas.backend.tenant.dto;

import com.saas.backend.address.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantRequest {
    private String name;
    private String slug;
    private String email;
    private String phone;
    private Address address;
    private String businessName;
    private String businessType;


}
