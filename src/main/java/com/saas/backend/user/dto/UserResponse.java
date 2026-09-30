package com.saas.backend.user.dto;

import com.saas.backend.user.entity.Role;
import com.saas.backend.user.entity.UserStatus;
import lombok.Data;



@Data
public class UserResponse {
    private Long id;
    private Long tenantId;
    private String name;
    private String email;
    private String phone;
    private Role role;
    private UserStatus status;

    public UserResponse(
            Long id,
            Long tenantId,
            String name,
            String email,
            String phone,
            Role role,
            UserStatus status
    ) {
        this.id = id;
        this.tenantId = tenantId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.role = role;
        this.status = status;
    }

}
