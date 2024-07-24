package com.hydroyura.prodms.archive.server.models.exceptions;

public class UnitNumberDuplicationException extends RuntimeException {

    private String number;

    public UnitNumberDuplicationException(String number, Throwable root) {
        super(root);
        this.number = number;
    }

    public String getNumber() {
        return number;
    }
}
