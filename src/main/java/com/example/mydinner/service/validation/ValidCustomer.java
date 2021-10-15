package com.example.mydinner.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidCustomerValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
public @interface ValidCustomer {
    String message() default "Invalid customer email";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
