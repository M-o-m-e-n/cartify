package org.cartify.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cartify.customer.exception.CustomerNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CustomerMapper mapper;

    public Page<CustomerResponse> getCustomersWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(mapper::fromCustomer);
    }

    public CustomerResponse getCustomer(String id) {
        return repository.findById(id)
                .map(mapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot find a customer with this id:" + id + "."

                ));
    }

    public String createCustomer(@Valid CustomerRequest request) {
        var customer = repository.save(mapper.toCustomer(request));
        return customer.getId();
    }


    public Customer updateCustomer(@Valid String id, @Valid CustomerRequest request) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot find a customer with this id:" + request.id() + "."
                ));
        customer.setFirstName(request.firstName());
        customer.setLastName(request.lastName());
        customer.setEmail(request.email());
        customer.setAddress(request.address());
        return repository.save(customer);
    }

    public void deleteCustomer(String id) {
        var customer = repository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(
                        "Cannot find a customer with this id:" + id + "."
                ));
        repository.delete(customer);
    }

}
