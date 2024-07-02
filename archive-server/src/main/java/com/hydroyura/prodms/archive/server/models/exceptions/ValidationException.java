package com.hydroyura.prodms.archive.server.models.exceptions;

import jakarta.validation.constraints.NotNull;
import org.springframework.validation.Errors;

public class ValidationException extends RuntimeException {

    private Errors errors;


    public ValidationException(@NotNull Errors errors) {
        super("Validation exception");
        this.errors = errors;
    }

    private ValidationException() {
        throw new RuntimeException("Don't use default constructor");
    }


    public Errors getErrors() {
        return errors;
    }
}
