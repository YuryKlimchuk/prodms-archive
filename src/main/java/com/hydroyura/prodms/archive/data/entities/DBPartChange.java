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

    // need to remove it, retain only in keys reference to part
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

    // Operation should be ENUM
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

    public DBPartChange setKey(DBPartChangeKey key) {
        this.key = key;
        return this;
    }

    public DBPart getPart() {
        return part;
    }

    public DBPartChange setPart(DBPart part) {
        this.part = part;
        return this;
    }

    public LocalDate getUpdate() {
        return update;
    }

    public DBPartChange setUpdate(LocalDate update) {
        this.update = update;
        return this;
    }

    public String getObject() {
        return object;
    }

    public DBPartChange setObject(String object) {
        this.object = object;
        return this;
    }

    public String getUser() {
        return user;
    }

    public DBPartChange setUser(String user) {
        this.user = user;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public DBPartChange setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getFieldName() {
        return fieldName;
    }

    public DBPartChange setFieldName(String fieldName) {
        this.fieldName = fieldName;
        return this;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public DBPartChange setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
        return this;
    }
}
