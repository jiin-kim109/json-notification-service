package com.jiin.httpqueue.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ActionValidator implements ConstraintValidator<ValidAction, String> {

    public boolean isValid(String action, ConstraintValidatorContext context) {
        return true;
    }
}
