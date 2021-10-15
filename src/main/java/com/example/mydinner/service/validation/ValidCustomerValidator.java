package com.example.mydinner.service.validation;

import com.example.mydinner.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidCustomerValidator implements ConstraintValidator<ValidCustomer, String> {
    @Autowired
    private CustomerService customerService;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        boolean valid = false;
        if(this.customerService.findById(username) != null) {
            valid = true;
        }
        return valid;
    }
}
