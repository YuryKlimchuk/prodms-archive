package com.hydroyura.prodms.archive.server.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "hist_units")
public class UnitHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;
    private String name;
    private String type;
    private String status;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime created;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDateTime updated;
    private Integer version;
    private String comment;
    @Column(name = "unit_number")
    private String unitNumber;

    public UnitHistory() {}


    public Long getId() {
        return id;
    }

    public UnitHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getOperation() {
        return operation;
    }

    public UnitHistory setOperation(String operation) {
        this.operation = operation;
        return this;
    }

    public String getName() {
        return name;
    }

    public UnitHistory setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public UnitHistory setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public UnitHistory setStatus(String status) {
        this.status = status;
        return this;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public UnitHistory setCreated(LocalDateTime created) {
        this.created = created;
        return this;
    }

    public LocalDateTime getUpdated() {
        return updated;
    }

    public UnitHistory setUpdated(LocalDateTime updated) {
        this.updated = updated;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public UnitHistory setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public UnitHistory setComment(String comment) {
        this.comment = comment;
        return this;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public UnitHistory setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
        return this;
    }
}
