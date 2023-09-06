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

    public void setAssemblyId(String assemblyId) {
        this.assemblyId = assemblyId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getReplacementId() {
        return replacementId;
    }

    public void setReplacementId(String replacementId) {
        this.replacementId = replacementId;
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
