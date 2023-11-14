package com.hydroyura.prodms.archive.data.entities.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.hydroyura.prodms.archive.data.entities.DBPart;
import com.hydroyura.prodms.archive.data.entities.keys.DBPartChangeKey;
import com.hydroyura.prodms.archive.services.publisher.partchange.PartChangeEventType;
import jakarta.persistence.*;

import java.time.LocalDate;

public class DTOPartChange {
    private DTOPart part;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate update;

    private String object;

    private String user;

    @Enumerated(EnumType.STRING)
    private PartChangeEventType operation;


    public DTOPartChange() {}


    public DTOPart getPart() {
        return part;
    }

    public DTOPartChange setPart(DTOPart part) {
        this.part = part;
        return this;
    }

    public LocalDate getUpdate() {
        return update;
    }

    public DTOPartChange setUpdate(LocalDate update) {
        this.update = update;
        return this;
    }

    public String getObject() {
        return object;
    }

    public DTOPartChange setObject(String object) {
        this.object = object;
        return this;
    }

    public String getUser() {
        return user;
    }

    public DTOPartChange setUser(String user) {
        this.user = user;
        return this;
    }

    public PartChangeEventType getOperation() {
        return operation;
    }

    public DTOPartChange setOperation(PartChangeEventType operation) {
        this.operation = operation;
        return this;
    }
}
