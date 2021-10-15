package com.example.mydinner.service;

import com.example.mydinner.dao.CustomerRepository;
import com.example.mydinner.entity.Customer;
import com.example.mydinner.rest.exception.ResourceRestBadRequestException;
import com.example.mydinner.rest.exception.ResourceRestNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Transactional
    public Customer findById(String id) {
        Optional<Customer> option = this.customerRepository.findById(id);
        Customer customer = null;

        if(option.isPresent()) {
            customer = option.get();
        }

        return customer;
    }

    @Transactional
    public void save(Customer customer) {
        if(findById(customer.getEmail()) != null) {
            throw new ResourceRestBadRequestException("Email already exists");
        }

        this.customerRepository.save(customer);
    }

    @Transactional
    public void update(Customer customer) {
        if(findById(customer.getEmail()) == null) {
            throw new ResourceRestNotFoundException("Customer not found");
        }

        this.customerRepository.save(customer);
    }
}
