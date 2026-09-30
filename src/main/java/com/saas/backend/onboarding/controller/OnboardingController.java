package com.saas.backend.onboarding.controller;

import com.saas.backend.onboarding.dto.OnboardingResponse;
import com.saas.backend.onboarding.service.OnboardingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/onboarding")
@CrossOrigin
public class OnboardingController {

    private final OnboardingService onboardingService;

    public OnboardingController(
            OnboardingService onboardingService
    ) {
        this.onboardingService = onboardingService;
    }

    @PostMapping("/{tenantId}/initialize")
    public ResponseEntity<OnboardingResponse> initialize(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                onboardingService.initialize(tenantId)
        );
    }

    @GetMapping("/{tenantId}")
    public ResponseEntity<OnboardingResponse> getProgress(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                onboardingService.getProgress(tenantId)
        );
    }

    @PutMapping("/{tenantId}/step/{step}/complete")
    public ResponseEntity<OnboardingResponse> completeStep(
            @PathVariable Long tenantId,
            @PathVariable Integer step
    ) {

        return ResponseEntity.ok(
                onboardingService.completeStep(
                        tenantId,
                        step
                )
        );
    }
}
