package com.hydroyura.prodms.archive.server.models.exceptions;

import jakarta.validation.constraints.NotNull;

public class InternalServerException extends RuntimeException {

    private Throwable root;

    public InternalServerException(@NotNull Throwable throwable) {
        super("Data base exception");
        this.root = throwable;
    }

    private InternalServerException() {
        throw new RuntimeException("Don't use default constructor");
    }

    public Throwable getRoot() {
        return root;
    }
}
