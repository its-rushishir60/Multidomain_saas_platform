package com.saas.backend.address.service;

import com.saas.backend.address.dto.AddressRequest;
import com.saas.backend.address.dto.AddressResponse;
import com.saas.backend.address.entity.Address;
import com.saas.backend.address.repository.AddressRepository;
import com.saas.backend.customer.entity.Customer;
import com.saas.backend.customer.repository.CustomerRepository;
import com.saas.backend.tenant.entity.Tenant;
import com.saas.backend.tenant.repository.TenantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;
    private final TenantRepository tenantRepository;
    private final CustomerRepository customerRepository;

    @Transactional
    public AddressResponse createAddress(AddressRequest request) {

        Address address = new Address();

        address.setAddress(request.getAddress());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setAddressType(request.getAddressType());

        /*
         * Tenant address
         */
        if (request.getTenantId() != null) {

            Tenant tenant = tenantRepository
                    .findById(request.getTenantId())
                    .orElseThrow(() ->
                            new RuntimeException("Tenant not found"));

            address.setTenant(tenant);

            // Because Tenant owns the relationship
            tenant.setAddress(address);

            tenantRepository.save(tenant);
        }

        /*
         * Customer address
         */
        if (request.getCustomerId() != null) {

            Customer customer = customerRepository
                    .findById(request.getCustomerId())
                    .orElseThrow(() ->
                            new RuntimeException("Customer not found"));

            address.setCustomer(customer);
        }

        Address savedAddress =
                addressRepository.save(address);

        return mapToResponse(savedAddress);
    }

    public AddressResponse getAddress(Long id) {

        Address address = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));

        return mapToResponse(address);
    }

    public List<AddressResponse> getTenantAddresses(
            Long tenantId
    ) {

        return addressRepository
                .findByTenantId(tenantId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public List<AddressResponse> getCustomerAddresses(
            Long customerId
    ) {

        return addressRepository
                .findByCustomerId(customerId)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Transactional
    public AddressResponse updateAddress(
            Long id,
            AddressRequest request
    ) {

        Address address = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));

        address.setAddress(request.getAddress());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setCountry(request.getCountry());
        address.setPincode(request.getPincode());
        address.setAddressType(request.getAddressType());

        Address updated =
                addressRepository.save(address);

        return mapToResponse(updated);
    }

    public void deleteAddress(Long id) {

        Address address = addressRepository
                .findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Address not found"));

        addressRepository.delete(address);
    }

    private AddressResponse mapToResponse(
            Address address
    ) {

        return new AddressResponse(
                address.getId(),
                address.getAddress(),
                address.getCity(),
                address.getState(),
                address.getCountry(),
                address.getPincode(),
                address.getAddressType(),
                address.getTenant() != null
                        ? address.getTenant().getId()
                        : null,
                address.getCustomer() != null
                        ? address.getCustomer().getId()
                        : null
        );
    }
}
