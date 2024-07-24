package com.hydroyura.prodms.archive.client.unit.request;

public class UnitCreateReq {

    private String number;
    private String name;
    private String type;
    private String status;
    private String comment;


    public UnitCreateReq() {}


    public String getNumber() {
        return number;
    }

    public UnitCreateReq setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public UnitCreateReq setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public UnitCreateReq setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UnitCreateReq setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public UnitCreateReq setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
