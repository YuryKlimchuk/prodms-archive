package com.hydroyura.prodms.archive.dto;

import com.hydroyura.prodms.archive.data.entities.DBPartStatus;
import com.hydroyura.prodms.archive.data.entities.DBPartType;

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


    public DTOPart() {}


    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public void setUpdated(LocalDate updated) {
        this.updated = updated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public void setOtherFile(String otherFile) {
        this.otherFile = otherFile;
    }

    public DBPartStatus getStatus() {
        return status;
    }

    public void setStatus(DBPartStatus status) {
        this.status = status;
    }

    public DBPartType getType() {
        return type;
    }

    public void setType(DBPartType type) {
        this.type = type;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
