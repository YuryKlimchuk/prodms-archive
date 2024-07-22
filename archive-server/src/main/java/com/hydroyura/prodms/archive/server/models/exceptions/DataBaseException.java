package com.hydroyura.prodms.archive.server.models.exceptions;

import jakarta.validation.constraints.NotNull;

public class DataBaseException extends RuntimeException {

    private Throwable root;

    public DataBaseException(@NotNull Throwable throwable) {
        super("Data base exception");
        this.root = throwable;
    }

    private DataBaseException() {
        throw new RuntimeException("Don't use default constructor");
    }

    public Throwable getRoot() {
        return root;
    }
}
