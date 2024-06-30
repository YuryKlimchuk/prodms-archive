package com.hydroyura.prodms.archive.server.models.exceptions;

import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

    private Errors errors;


    public ValidationException(Errors errors) {
        super("Validation exception");
        this.errors = errors;
    }

    private ValidationException() {
        throw new RuntimeException("Don't use default constructor");
    }

}
