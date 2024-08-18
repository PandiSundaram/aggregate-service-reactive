package com.pandi.reactive.aggrigator.service.annotation;

import com.pandi.reactive.aggrigator.service.domain.Ticker;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class ValidTickerImplementation implements ConstraintValidator<ValidTicker, Ticker> {
    @Override
    public void initialize(ValidTicker constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Ticker ticker, ConstraintValidatorContext constraintValidatorContext) {
       if(Objects.isNull(ticker)){
           return false;
       }
       return true;
    }
}
