package com.hydroyura.prodms.archive.client.dtos.unit.dto;

public class DTOUnitCreate {
    private String number;
    private String name;
    private String type;
    private String status;
    private String comment;


    public DTOUnitCreate() {}


    public String getNumber() {
        return number;
    }

    public DTOUnitCreate setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public DTOUnitCreate setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public DTOUnitCreate setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public DTOUnitCreate setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public DTOUnitCreate setComment(String comment) {
        this.comment = comment;
        return this;
    }
}