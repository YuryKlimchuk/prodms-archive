package com.hydroyura.prodms.archive.client.dtos.unit.dto;

public class DTOUnit {

    private String number;
    private String name;
    private String type;
    private String status;


    public DTOUnit() {}


    public String getNumber() {
        return number;
    }

    public DTOUnit setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public DTOUnit setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public DTOUnit setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public DTOUnit setStatus(String status) {
        this.status = status;
        return this;
    }
}
