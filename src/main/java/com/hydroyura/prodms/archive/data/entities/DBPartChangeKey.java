package com.hydroyura.prodms.archive.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class DBPartChangeKey implements Serializable {

    @Column(name = "version")
    private long version;
    @Column(name = "part_number")
    private String partNumber;


    public DBPartChangeKey() {}

    public long getVersion() {
        return version;
    }

    public DBPartChangeKey setVersion(long version) {
        this.version = version;
        return this;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public DBPartChangeKey setPartNumber(String partNumber) {
        this.partNumber = partNumber;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DBPartChangeKey that = (DBPartChangeKey) o;
        return version == that.version && Objects.equals(partNumber, that.partNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version, partNumber);
    }
}
