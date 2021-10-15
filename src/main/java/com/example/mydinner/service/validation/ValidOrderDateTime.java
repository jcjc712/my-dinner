package com.example.mydinner.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = ValidOrderDateTimeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Documented
public @interface ValidOrderDateTime {
    String message() default "System offline";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
