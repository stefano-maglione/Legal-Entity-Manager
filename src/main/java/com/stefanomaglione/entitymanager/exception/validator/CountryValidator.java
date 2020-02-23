package com.stefanomaglione.entitymanager.exception.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CountryValidator implements ConstraintValidator<Country, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isEmpty()) {
            return true;
        }

        for (Countries c : Countries.values()) {
            if (c.name().equals(value)) {
                return true;
            }
        }

        return false;
    }
}
