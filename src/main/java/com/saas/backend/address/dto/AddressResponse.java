package com.saas.backend.address.dto;

import com.saas.backend.address.entity.AddressType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private String address;

    private String city;

    private String state;

    private String country;

    private String pincode;

    private AddressType addressType;

    private Long tenantId;

    private Long customerId;
}
