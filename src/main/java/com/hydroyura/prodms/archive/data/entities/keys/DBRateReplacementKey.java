package com.hydroyura.prodms.archive.data.entities.keys;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DBRateReplacementKey implements Serializable {

    @Column(name = "assembly_id")
    private String assemblyId;

    @Column(name = "element_id")
    private String elementId;

    @Column(name = "replacement_id")
    private String replacementId;


    public DBRateReplacementKey() {}


    public String getAssemblyId() {
        return assemblyId;
    }

    public DBRateReplacementKey setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
        return this;
    }

    public String getElementId() {
        return elementId;
    }

    public DBRateReplacementKey setElementId(String elementId) {
        this.elementId = elementId;
        return this;
    }

    public String getReplacementId() {
        return replacementId;
    }

    public DBRateReplacementKey setReplacementId(String replacementId) {
        this.replacementId = replacementId;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBRateReplacementKey that = (DBRateReplacementKey) o;
        return Objects.equals(assemblyId, that.assemblyId) && Objects.equals(elementId, that.elementId) && Objects.equals(replacementId, that.replacementId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(assemblyId, elementId, replacementId);
    }
}
