package com.hydroyura.prodms.archive.server.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Table(name = "units")
@Entity
public class Unit {

    @Id
    private String number;
    private String name;
    private String type;
    private String status;
    private ZonedDateTime created;
    private ZonedDateTime updated;
    private Integer version;
    private String comment;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_number")
    private Set<UnitHistory> history = new HashSet<>();


    public Unit() {}


    public String getNumber() {
        return number;
    }

    public Unit setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public Unit setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Unit setType(String type) {
        this.type = type;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public Unit setStatus(String status) {
        this.status = status;
        return this;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public Unit setCreated(ZonedDateTime created) {
        this.created = created;
        return this;
    }

    public ZonedDateTime getUpdated() {
        return updated;
    }

    public Unit setUpdated(ZonedDateTime updated) {
        this.updated = updated;
        return this;
    }

    public Integer getVersion() {
        return version;
    }

    public Unit setVersion(Integer version) {
        this.version = version;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public Unit setComment(String comment) {
        this.comment = comment;
        return this;
    }
}
