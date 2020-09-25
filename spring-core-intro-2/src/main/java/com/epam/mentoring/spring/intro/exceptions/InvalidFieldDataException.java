package com.epam.mentoring.spring.intro.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;

import java.util.List;

public class InvalidFieldDataException extends RuntimeException {

    private static final Logger log = LoggerFactory.getLogger(InvalidFieldDataException.class);

    private List<FieldError> errors;

    public InvalidFieldDataException(String msg, List<FieldError> errors) {
        super(msg);
        this.setErrors(errors);

        for (FieldError error : this.errors) {
            log.error("The value '{}' of field '{}' invalid: {}", error.getRejectedValue(), error.getField(), error.getDefaultMessage());
        }
    }

    public List<FieldError> getErrors() {
        return this.errors;
    }

    public void setErrors(List<FieldError> errors) {
        this.errors = errors;
    }

}
