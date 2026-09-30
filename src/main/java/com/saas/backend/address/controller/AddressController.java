package com.saas.backend.address.controller;

import com.saas.backend.address.dto.AddressRequest;
import com.saas.backend.address.dto.AddressResponse;
import com.saas.backend.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(
            @RequestBody AddressRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(addressService.createAddress(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddress(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                addressService.getAddress(id)
        );
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<AddressResponse>> getTenantAddresses(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                addressService.getTenantAddresses(tenantId)
        );
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<AddressResponse>> getCustomerAddresses(
            @PathVariable Long customerId
    ) {

        return ResponseEntity.ok(
                addressService.getCustomerAddresses(customerId)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable Long id,
            @RequestBody AddressRequest request
    ) {

        return ResponseEntity.ok(
                addressService.updateAddress(id, request)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable Long id
    ) {

        addressService.deleteAddress(id);

        return ResponseEntity.noContent().build();
    }
}