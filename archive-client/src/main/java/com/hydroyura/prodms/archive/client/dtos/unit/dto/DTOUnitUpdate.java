package com.hydroyura.prodms.archive.client.dtos.unit.dto;

public class DTOUnitUpdate {

    private String number;
    private String name;
    private String status;
    private String comment;


    public DTOUnitUpdate() {}


    public String getNumber() {
        return number;
    }

    public DTOUnitUpdate setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public DTOUnitUpdate setName(String name) {
        this.name = name;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public DTOUnitUpdate setStatus(String status) {
        this.status = status;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public DTOUnitUpdate setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
