package com.hydroyura.prodms.archive.data.entities.keys;

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


    public DBRateKey() {}

    public String getAssemblyId() {
        return assemblyId;
    }

    public DBRateKey setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
        return this;
    }

    public String getElementId() {
        return elementId;
    }

    public DBRateKey setElementId(String elementId) {
        this.elementId = elementId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBRateKey dbRateKey = (DBRateKey) o;
        return Objects.equals(assemblyId, dbRateKey.assemblyId) && Objects.equals(elementId, dbRateKey.elementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assemblyId, elementId);
    }
}
