package com.hydroyura.prodms.archive.client.dtos.unit.filter;

import java.util.ArrayList;
import java.util.Collection;

public class FilterUnit {

    private String number, name;
    private Collection<String> types, statuses = new ArrayList<>();

    private String sort, sortOrder;
    private Integer page, perPage;


    public FilterUnit() {}


    public String getNumber() {
        return number;
    }

    public FilterUnit setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getName() {
        return name;
    }

    public FilterUnit setName(String name) {
        this.name = name;
        return this;
    }

    public Collection<String> getTypes() {
        return types;
    }

    public FilterUnit setTypes(Collection<String> types) {
        this.types = types;
        return this;
    }

    public Collection<String> getStatuses() {
        return statuses;
    }

    public FilterUnit setStatuses(Collection<String> statuses) {
        this.statuses = statuses;
        return this;
    }

    public String getSort() {
        return sort;
    }

    public FilterUnit setSort(String sort) {
        this.sort = sort;
        return this;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public FilterUnit setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
        return this;
    }

    public Integer getPage() {
        return page;
    }

    public FilterUnit setPage(Integer page) {
        this.page = page;
        return this;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public FilterUnit setPerPage(Integer perPage) {
        this.perPage = perPage;
        return this;
    }
}
