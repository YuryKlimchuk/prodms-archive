package com.hydroyura.prodms.archive.data.entities;

import com.hydroyura.prodms.archive.data.entities.keys.DBRateKey;
import jakarta.persistence.*;

@Entity @Table(name = "assembly_rates")
public class DBRate {

    @EmbeddedId
    private DBRateKey key;

    @ManyToOne
    @MapsId("assemblyId")
    @JoinColumn(name = "assembly_id")
    private DBPart assembly;

    @ManyToOne
    @MapsId("elementId")
    @JoinColumn(name = "element_id")
    private DBPart element;

    @Column(name = "count")
    private long count;


    public DBRate() {}


    public DBRateKey getKey() {
        return key;
    }

    public DBRate setKey(DBRateKey key) {
        this.key = key;
        return this;
    }

    public DBPart getAssembly() {
        return assembly;
    }

    public DBRate setAssembly(DBPart assembly) {
        this.assembly = assembly;
        return this;
    }

    public DBPart getElement() {
        return element;
    }

    public DBRate setElement(DBPart element) {
        this.element = element;
        return this;
    }

    public long getCount() {
        return count;
    }

    public DBRate setCount(long count) {
        this.count = count;
        return this;
    }
}
