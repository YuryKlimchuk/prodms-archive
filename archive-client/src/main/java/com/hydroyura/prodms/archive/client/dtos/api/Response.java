package com.hydroyura.prodms.archive.client.dtos.api;

public class Response {

    private String status;
    private Object content;


    public Response() {}


    public String getStatus() {
        return status;
    }

    public Response setStatus(String status) {
        this.status = status;
        return this;
    }

    public Object getContent() {
        return content;
    }

    public Response setContent(Object content) {
        this.content = content;
        return this;
    }
}
