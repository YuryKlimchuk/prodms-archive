package com.hydroyura.prodms.archive.data.entities;

import com.hydroyura.prodms.archive.data.entities.keys.DBRateReplacementKey;
import jakarta.persistence.*;

@Entity
@Table(name = "rate_replacements")
public class DBRateReplacement {

    @EmbeddedId
    private DBRateReplacementKey key;

    @ManyToOne
    @MapsId("assemblyId")
    @JoinColumn(name = "assembly_id")
    private DBPart assembly;

    @ManyToOne
    @MapsId("elementId")
    @JoinColumn(name = "element_id")
    private DBPart element;

    @ManyToOne
    @MapsId("replacementId")
    @JoinColumn(name = "replacement_id")
    private DBPart replacement;

    @Column(name = "priority")
    private long priority;


    public DBRateReplacement() {}


    public DBRateReplacementKey getKey() {
        return key;
    }

    public DBRateReplacement setKey(DBRateReplacementKey key) {
        this.key = key;
        return this;
    }

    public DBPart getAssembly() {
        return assembly;
    }

    public DBRateReplacement setAssembly(DBPart assembly) {
        this.assembly = assembly;
        return this;
    }

    public DBPart getElement() {
        return element;
    }

    public DBRateReplacement setElement(DBPart element) {
        this.element = element;
        return this;
    }

    public DBPart getReplacement() {
        return replacement;
    }

    public DBRateReplacement setReplacement(DBPart replacement) {
        this.replacement = replacement;
        return this;
    }

    public long getPriority() {
        return priority;
    }

    public DBRateReplacement setPriority(long priority) {
        this.priority = priority;
        return this;
    }
}
