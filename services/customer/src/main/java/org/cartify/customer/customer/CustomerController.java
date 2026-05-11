package org.cartify.customer.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;


    @GetMapping
    public ResponseEntity<Page<CustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(service.getCustomersWithPagination(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerResponse> getCustomer(
            @PathVariable("id") String id
    ){
      return ResponseEntity.ok(service.getCustomer(id));
    }


    @PostMapping
    public ResponseEntity<String> createCustomer(
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(service.createCustomer(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(
            @PathVariable @Valid String id,
            @RequestBody @Valid CustomerRequest request
    ) {
        return ResponseEntity.ok(service.updateCustomer(id, request));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(
            @PathVariable String id
    ){
        service.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }

}
