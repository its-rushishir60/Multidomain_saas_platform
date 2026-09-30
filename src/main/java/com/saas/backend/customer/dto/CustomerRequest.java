package com.saas.backend.customer.dto;

import lombok.Data;

@Data
public class CustomerRequest {
    private String name;
    private String email;
    private String phone;
    private Long tenantId;
}
