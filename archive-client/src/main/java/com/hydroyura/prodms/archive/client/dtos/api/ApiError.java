package com.hydroyura.prodms.archive.client.dtos.api;

import java.util.Set;

public class ApiError {

    private String description;
    private Set<String> errors;


    public ApiError() {}


    public String getDescription() {
        return description;
    }

    public ApiError setDescription(String description) {
        this.description = description;
        return this;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public ApiError setErrors(Set<String> errors) {
        this.errors = errors;
        return this;
    }
}
