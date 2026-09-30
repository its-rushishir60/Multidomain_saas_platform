package com.saas.backend.tenant.controller;


import com.saas.backend.tenant.dto.KycRequest;
import com.saas.backend.tenant.dto.KycResponse;
import com.saas.backend.tenant.service.KycService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/kyc")
@CrossOrigin
public class KycController {

    private final KycService kycService;

    public KycController(KycService kycService) {
        this.kycService = kycService;
    }

    @PostMapping
    public ResponseEntity<KycResponse> submitKyc(
            @RequestBody KycRequest request
    ) {

        return ResponseEntity.ok(
                kycService.submitKyc(request)
        );
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<KycResponse> getKyc(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                kycService.getKyc(tenantId)
        );
    }

    @PutMapping("/tenant/{tenantId}/verify")
    public ResponseEntity<KycResponse> verifyKyc(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                kycService.verifyKyc(tenantId)
        );
    }
}
