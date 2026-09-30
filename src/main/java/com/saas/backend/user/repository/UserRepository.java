package com.saas.backend.user.repository;

import com.saas.backend.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByEmail(String email);
    List<User> findByTenantId(Long tenantId);
    boolean existsByEmail(String email);
}
