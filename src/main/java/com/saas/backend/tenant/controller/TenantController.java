package com.saas.backend.tenant.controller;

import com.saas.backend.tenant.dto.TenantRequest;
import com.saas.backend.tenant.dto.TenantResponse;
import com.saas.backend.tenant.service.TenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/tenants")
@CrossOrigin
public class TenantController {

    @Autowired
    private TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;

    }

    @PostMapping
    public ResponseEntity<TenantResponse> createTenant(
            @RequestBody TenantRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tenantService.createTenant(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TenantResponse> getTenant(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                tenantService.getTenant(id)
        );
    }

}
