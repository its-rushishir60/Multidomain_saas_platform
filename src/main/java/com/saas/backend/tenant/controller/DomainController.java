package com.saas.backend.tenant.controller;

import com.saas.backend.tenant.dto.DomainRequest;
import com.saas.backend.tenant.dto.DomainResponse;
import com.saas.backend.tenant.service.DomainService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/domains")
@CrossOrigin
public class DomainController {
    private final DomainService domainService;

    public DomainController(
            DomainService domainService
    ) {
        this.domainService = domainService;
    }

    @PostMapping
    public ResponseEntity<DomainResponse> addDomain(
            @RequestBody DomainRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(domainService.addDomain(request));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<DomainResponse>> getDomains(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                domainService.getTenantDomains(tenantId)
        );
    }
}
