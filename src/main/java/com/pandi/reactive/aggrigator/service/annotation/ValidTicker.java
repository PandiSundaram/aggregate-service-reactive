package com.pandi.reactive.aggrigator.service.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidTickerImplementation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ValidTicker {
    String message() default "invalid enum";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
