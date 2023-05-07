package com.hydroyura.prodms.archive.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DBRateKey implements Serializable {

    @Column(name = "assembly_id")
    private String assemblyId;

    @Column(name = "element_id")
    private String elementId;

    @Column(name = "replacement")
    private long replacement = 0;


    public DBRateKey() {}


    public String getAssemblyId() {
        return assemblyId;
    }

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public long getReplacement() {
        return replacement;
    }

    public void setReplacement(long replacement) {
        this.replacement = replacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBRateKey dbRateKey = (DBRateKey) o;
        return replacement == dbRateKey.replacement && Objects.equals(assemblyId, dbRateKey.assemblyId) && Objects.equals(elementId, dbRateKey.elementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assemblyId, elementId, replacement);
    }
}
