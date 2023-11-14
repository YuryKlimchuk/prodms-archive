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

    public void setKey(DBRateKey key) {
        this.key = key;
    }

    public DBPart getAssembly() {
        return assembly;
    }

    public void setAssembly(DBPart assembly) {
        this.assembly = assembly;
    }

    public DBPart getElement() {
        return element;
    }

    public void setElement(DBPart element) {
        this.element = element;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
