package com.saas.backend.customer.repository;

import com.saas.backend.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByTenantId(Long tenantId);

    Optional<Customer> findByEmailAndTenantId(String email, Long tenantId);

    boolean existsByEmailAndTenantId(String email, Long tenantId);


}
