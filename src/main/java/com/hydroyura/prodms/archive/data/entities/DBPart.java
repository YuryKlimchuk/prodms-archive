package com.hydroyura.prodms.archive.data.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

@Entity @Table(name = "parts")
public class DBPart {

    @Id
    private String number;
    @Column
    private long version;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column
    private LocalDate created;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Column(name = "last_update")
    private LocalDate updated;
    @Column
    private String name;
    @Column
    private String pdf;
    @Column(name = "other_file")
    private String otherFile;
    @Column @Enumerated(EnumType.STRING)
    private DBPartStatus status;
    @Column @Enumerated(EnumType.STRING)
    private DBPartType type;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "part", fetch = FetchType.EAGER)
    private Collection<DBPartChange> changes = new ArrayList<>();
    @Column
    private String info;


    public DBPart() {
        setPdf("N/A");
        setOtherFile("N/A");
        setCreated(LocalDate.now());
        setUpdated(LocalDate.now());
        setVersion(1L);
    }


    public String getNumber() {
        return number;
    }

    public DBPart setNumber(String number) {
        this.number = number;
        return this;
    }

    public long getVersion() {
        return version;
    }

    public DBPart setVersion(long version) {
        this.version = version;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public DBPart setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public LocalDate getUpdated() {
        return updated;
    }

    public DBPart setUpdated(LocalDate updated) {
        this.updated = updated;
        return this;
    }

    public String getName() {
        return name;
    }

    public DBPart setName(String name) {
        this.name = name;
        return this;
    }

    public String getPdf() {
        return pdf;
    }

    public DBPart setPdf(String pdf) {
        this.pdf = pdf;
        return this;
    }

    public String getOtherFile() {
        return otherFile;
    }

    public DBPart setOtherFile(String otherFile) {
        this.otherFile = otherFile;
        return this;
    }

    public DBPartStatus getStatus() {
        return status;
    }

    public DBPart setStatus(DBPartStatus status) {
        this.status = status;
        return this;
    }

    public DBPartType getType() {
        return type;
    }

    public DBPart setType(DBPartType type) {
        this.type = type;
        return this;
    }

    public Collection<DBPartChange> getChanges() {
        return changes;
    }

    public DBPart setChanges(Collection<DBPartChange> changes) {
        this.changes = changes;
        return this;
    }

    public String getInfo() {
        return info;
    }

    public DBPart setInfo(String info) {
        this.info = info;
        return this;
    }
}