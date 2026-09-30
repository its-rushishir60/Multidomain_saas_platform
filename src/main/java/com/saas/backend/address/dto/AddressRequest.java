package com.saas.backend.address.dto;

import com.saas.backend.address.entity.AddressType;
import lombok.Data;

@Data
public class AddressRequest {

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private AddressType addressType;

    private Long tenantId;

    private Long customerId;
}
