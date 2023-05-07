package com.hydroyura.prodms.archive.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity @Table(name = "parts_changes")
public class DBPartChange {

    @EmbeddedId
    private DBPartChangeKey key;
    @ManyToOne
    @MapsId("part_number")
    private DBPart part;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "last_update")
    private LocalDate update;
    @Column(name = "object_json")
    private String object;
    @Column(name = "user_name")
    private String user;
    @Column
    private String operation;
    @Column(name = "field_name")
    private String fieldName;
    @Column(name = "field_value")
    private String fieldValue;


    public DBPartChange() {}


    public DBPartChangeKey getKey() {
        return key;
    }

    public void setKey(DBPartChangeKey key) {
        this.key = key;
    }

    public DBPart getPart() {
        return part;
    }

    public void setPart(DBPart part) {
        this.part = part;
    }

    public LocalDate getUpdate() {
        return update;
    }

    public void setUpdate(LocalDate update) {
        this.update = update;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }
}
