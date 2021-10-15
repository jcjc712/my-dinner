package com.example.mydinner.service.validation;

import com.example.mydinner.entity.OrderDetail;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Set;

@Component
public class AtLeastTwoElementsValidator implements ConstraintValidator<AtLeastTwoElements, Set<OrderDetail>> {

    @Override
    public boolean isValid(Set<OrderDetail> items, ConstraintValidatorContext context) {
        if(items.size() >= 2) {
            return true;
        }
        return false;
    }
}
