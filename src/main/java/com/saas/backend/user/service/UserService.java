package com.saas.backend.user.service;

import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.repository.TenantRepository;
import com.saas.backend.user.dto.UserRequest;
import com.saas.backend.user.dto.UserResponse;
import com.saas.backend.user.entity.Role;
import com.saas.backend.user.entity.User;
import com.saas.backend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TenantRepository tenantRepository;

    public UserService(
            UserRepository userRepository,
            TenantRepository tenantRepository
    ){
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
    }

    @Transactional
    public UserResponse createOwner(UserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("User email already exists");
        }

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        User user = new User();

        user.setTenant(tenant);
        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Temporary for Phase 1.
        // Password encoding/JWT will be implemented in the Security phase.
        user.setPassword(request.getPassword());

        user.setPhone(request.getPhone());
        user.setRole(Role.TENANT_OWNER);

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public List<UserResponse> getUsersByTenant(Long tenantId) {

        return userRepository.findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getTenant() != null
                        ? user.getTenant().getId()
                        : null,
                user.getName(),
                user.getEmail(),
                user.getPhone(),
                user.getRole(),
                user.getStatus()
        );
    }

}
