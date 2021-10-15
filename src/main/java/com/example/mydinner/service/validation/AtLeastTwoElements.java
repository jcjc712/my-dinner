package com.example.mydinner.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = AtLeastTwoElementsValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
public @interface AtLeastTwoElements {
    String message() default "It should have at least two elements";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
