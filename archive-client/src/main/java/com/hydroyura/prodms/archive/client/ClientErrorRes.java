package com.hydroyura.prodms.archive.client;

import java.util.HashSet;
import java.util.Set;

public class ClientErrorRes {

    private String timestamp;
    private Set<String> errors = new HashSet<>();
    private String path;


    public ClientErrorRes() {}


    public String getTimestamp() {
        return timestamp;
    }

    public ClientErrorRes setTimestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Set<String> getErrors() {
        return errors;
    }

    public ClientErrorRes setErrors(Set<String> errors) {
        this.errors = errors;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ClientErrorRes setPath(String path) {
        this.path = path;
        return this;
    }
}
