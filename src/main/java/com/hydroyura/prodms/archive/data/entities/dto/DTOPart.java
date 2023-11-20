package com.hydroyura.prodms.archive.data.entities.dto;

import com.hydroyura.prodms.archive.data.entities.enums.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.enums.DBPartType;

import java.time.LocalDate;

public class DTOPart {

    private long version;
    private LocalDate created;
    private LocalDate updated;
    private String name;
    private String number;
    private String pdf;
    private String otherFile;
    private DBPartStatus status;
    private DBPartType type;
    private String info;


    public DTOPart() {
        setVersion(1L);
        setCreated(LocalDate.now());
        setUpdated(LocalDate.now());
        setPdf("N/A");
        setOtherFile("N/A");
        setInfo("");
    }


    public long getVersion() {
        return version;
    }

    public DTOPart setVersion(long version) {
        this.version = version;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public DTOPart setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public DTOPart setUpdated(LocalDate updated) {
        this.updated = updated;
        return this;
    }

    public String getName() {
        return name;
    }

    public DTOPart setName(String name) {
        this.name = name;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public DTOPart setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getPdf() {
        return pdf;
    }

    public DTOPart setPdf(String pdf) {
        this.pdf = pdf;
        return this;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public DTOPart setOtherFile(String otherFile) {
        this.otherFile = otherFile;
        return this;
    }

    public DBPartStatus getStatus() {
        return status;
    }

    public DTOPart setStatus(DBPartStatus status) {
        this.status = status;
        return this;
    }

    public DBPartType getType() {
        return type;
    }

    public DTOPart setType(DBPartType type) {
        this.type = type;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public DTOPart setInfo(String info) {
        this.info = info;
        return this;
    }
}
