package com.saas.backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private Long tenantId;
    private String name;
    private String email;
    private String password;
    private String phone;


}
