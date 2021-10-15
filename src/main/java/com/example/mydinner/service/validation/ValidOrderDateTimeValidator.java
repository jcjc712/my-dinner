package com.example.mydinner.service.validation;

import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
public class ValidOrderDateTimeValidator implements ConstraintValidator<ValidOrderDateTime, Timestamp> {

    @Override
    public boolean isValid(Timestamp orderedAt, ConstraintValidatorContext context) {
        ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("America/Mexico_City")) ;
        Timestamp from = Timestamp.valueOf(zdt.withHour(16).withMinute(0).withSecond(0).toLocalDateTime());
        Timestamp to = Timestamp.valueOf(zdt.withHour(21).withMinute(0).withSecond(0).toLocalDateTime());

        if (orderedAt.after(from) && orderedAt.before(to)) {
            return true;
        }
        return false;
    }
}
