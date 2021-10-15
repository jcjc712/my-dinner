package com.example.mydinner.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidDishValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
public @interface ValidDish {
    String message() default "Invalid or disabled dish";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
