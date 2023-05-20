package com.hydroyura.prodms.archive.dto;

public class DTORate {

    private long count;
    private long replacement;
    private long priority;
    private DTOPart element;
    private DTOPart assembly;


    public DTORate() {}


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getReplacement() {
        return replacement;
    }

    public void setReplacement(long replacement) {
        this.replacement = replacement;
    }

    public DTOPart getElement() {
        return element;
    }

    public void setElement(DTOPart element) {
        this.element = element;
    }

    public long getPriority() {
        return priority;
    }

    public void setPriority(long priority) {
        this.priority = priority;
    }

    public DTOPart getAssembly() {
        return assembly;
    }

    public void setAssembly(DTOPart assembly) {
        this.assembly = assembly;
    }
}
