package com.saas.backend.customer.controller;

import com.saas.backend.customer.dto.CustomerRequest;
import com.saas.backend.customer.dto.CustomerResponse;
import com.saas.backend.customer.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;


    // CREATE CUSTOMER
    @PostMapping
    public ResponseEntity<CustomerResponse> createCustomer(
            @RequestBody CustomerRequest request) {

        CustomerResponse response =
                customerService.createCustomer(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET CUSTOMER BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomerById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                customerService.getCustomerById(id)
        );
    }


    // GET ALL CUSTOMERS OF TENANT
    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<CustomerResponse>> getCustomersByTenant(
            @PathVariable Long tenantId) {

        return ResponseEntity.ok(
                customerService.getCustomersByTenant(tenantId)
        );
    }


    // UPDATE CUSTOMER
    @PutMapping("/{id}")
    public ResponseEntity<CustomerResponse> updateCustomer(
            @PathVariable Long id,
            @RequestBody CustomerRequest request) {

        return ResponseEntity.ok(
                customerService.updateCustomer(id, request)
        );
    }


    // DELETE CUSTOMER
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(
            @PathVariable Long id) {

        customerService.deleteCustomer(id);

        return ResponseEntity.ok(
                "Customer deleted successfully"
        );
    }
}
