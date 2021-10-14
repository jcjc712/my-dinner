package com.example.mydinner.rest;

import com.example.mydinner.entity.Customer;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import com.example.mydinner.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> findAll() {
        return customerService.findAll();
    }

    @GetMapping("/customers/{customerId}")
    public Customer get(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);
        if(customer == null) {
            throw new ResourceRestNotFoundException("Checklist not found with id " + customerId);
        }
        return customer;
    }

    @PostMapping("/customers")
    public Customer add(@Valid @RequestBody Customer customer) {
        // todo unique email id
        customerService.save(customer);
        return customer;
    }

    @PutMapping("/customers")
    public Customer update(@Valid @RequestBody Customer checklist) {
        customerService.save(checklist);
        return checklist;
    }

    @DeleteMapping("/customers/{customerId}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable String customerId) {
        Customer customer = customerService.findById(customerId);
        if(customer == null) {
            throw new ResourceRestNotFoundException("Checklist not found with id " + customerId);
        }
        customerService.deleteById(customerId);
        Map<String, String> message = new HashMap<>();
        message.put("message", String.format("Customer %s deleted", customerId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
