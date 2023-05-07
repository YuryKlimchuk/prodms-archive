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

    @Id @Column
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

    /*
    @OneToMany(mappedBy = "assembly", fetch = FetchType.EAGER)
    private Set<DBRate> assemblies;
    @OneToMany(mappedBy = "element", fetch = FetchType.EAGER)
    private Set<DBRate> elements;
    */

    public DBPart() {}


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

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

    public Collection<DBPartChange> getChanges() {
        return changes;
    }

    public void setChanges(Collection<DBPartChange> changes) {
        this.changes = changes;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    /*
    public Set<DBRate> getAssemblies() {
        return assemblies;
    }

    public void setAssemblies(Set<DBRate> assemblies) {
        this.assemblies = assemblies;
    }

    public Set<DBRate> getElements() {
        return elements;
    }

    public void setElements(Set<DBRate> elements) {
        this.elements = elements;
    }
     */
}