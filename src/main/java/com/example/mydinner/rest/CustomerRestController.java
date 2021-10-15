package com.example.mydinner.rest;

import com.example.mydinner.entity.Customer;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/{customerId}")
    public Customer get(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);
        if(customer == null) {
            throw new ResourceRestNotFoundException("Checklist not found with id " + customerId);
        }
        return customer;
    }

    @PostMapping
    public Customer add(@Valid @RequestBody Customer customer) {
        customerService.save(customer);
        return customer;
    }

    @PutMapping
    public Customer update(@Valid @RequestBody Customer customer) {
        customerService.update(customer);
        return customer;
    }
}
