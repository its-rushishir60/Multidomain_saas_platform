package com.saas.backend.user.controller;

import com.saas.backend.user.dto.UserRequest;
import com.saas.backend.user.dto.UserResponse;
import com.saas.backend.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/owner")
    public ResponseEntity<UserResponse> createOwner(
            @RequestBody UserRequest request
    ) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.createOwner(request));
    }

    @GetMapping("/tenant/{tenantId}")
    public ResponseEntity<List<UserResponse>> getTenantUsers(
            @PathVariable Long tenantId
    ) {

        return ResponseEntity.ok(
                userService.getUsersByTenant(tenantId)
        );
    }
}
