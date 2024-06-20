package com.hydroyura.prodms.archive.client.dtos.unit.dto;

public class DTOUnitPartialUpdate {

    private String number;
    private String fieldName;
    private String newValue;


    public DTOUnitPartialUpdate() {}


    public String getNumber() {
        return number;
    }

    public DTOUnitPartialUpdate setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DTOUnitPartialUpdate setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getNewValue() {
        return newValue;
    }

    public DTOUnitPartialUpdate setNewValue(String newValue) {
        this.newValue = newValue;
        return this;
    }
}
