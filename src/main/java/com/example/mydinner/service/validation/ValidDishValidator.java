package com.example.mydinner.service.validation;

import com.example.mydinner.entity.Dish;
import com.example.mydinner.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class ValidDishValidator implements ConstraintValidator<ValidDish, Long> {
    @Autowired
    private DishService dishService;

    @Override
    public boolean isValid(Long dishId, ConstraintValidatorContext context) {
        boolean valid = false;
        Dish dish = dishService.findById(dishId);
        if(dish != null && dish.getStatus().equals(Dish.StatusTypes.available.name())) {
            valid = true;
        }
        return valid;
    }
}
