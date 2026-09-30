package com.saas.backend.customer.service;

import com.saas.backend.customer.dto.CustomerRequest;
import com.saas.backend.customer.dto.CustomerResponse;
import com.saas.backend.customer.entity.Customer;
import com.saas.backend.customer.repository.CustomerRepository;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final TenantRepository tenantRepository;

    // CREATE CUSTOMER
    public CustomerResponse createCustomer(CustomerRequest request) {

        Tenant tenant = tenantRepository.findById(request.getTenantId())
                .orElseThrow(() ->
                        new RuntimeException("Tenant not found"));

        if (customerRepository.existsByEmailAndTenantId(
                request.getEmail(),
                request.getTenantId())) {

            throw new RuntimeException(
                    "Customer with this email already exists in this store");
        }

        Customer customer = new Customer();

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setTenant(tenant);

        Customer savedCustomer = customerRepository.save(customer);

        return mapToResponse(savedCustomer);
    }


    // GET CUSTOMER BY ID
    public CustomerResponse getCustomerById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return mapToResponse(customer);
    }


    // GET ALL CUSTOMERS OF TENANT
    public List<CustomerResponse> getCustomersByTenant(Long tenantId) {

        return customerRepository.findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    // UPDATE CUSTOMER
    public CustomerResponse updateCustomer(
            Long id,
            CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());

        Customer updatedCustomer =
                customerRepository.save(customer);

        return mapToResponse(updatedCustomer);
    }


    // DELETE CUSTOMER
    public void deleteCustomer(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        customerRepository.delete(customer);
    }


    // ENTITY → RESPONSE
    private CustomerResponse mapToResponse(Customer customer) {

        return new CustomerResponse(
                customer.getId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getTenant().getId()
        );
    }
}
