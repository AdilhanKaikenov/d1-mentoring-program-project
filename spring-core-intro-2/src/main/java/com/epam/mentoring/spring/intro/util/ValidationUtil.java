package com.epam.mentoring.spring.intro.util;

import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

public final class ValidationUtil {

    public static BindingResult validate(Object target, Validator validator) {
        DataBinder binder = new DataBinder(target);
        binder.setValidator(validator);
        binder.validate();
        return binder.getBindingResult();
    }

}
