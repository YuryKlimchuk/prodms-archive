package com.hydroyura.prodms.archive.server.controllers;

public class TestRequestBody {

    private String name;
    private Integer age;

    public TestRequestBody() {}

    public String getName() {
        return name;
    }

    public TestRequestBody setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getAge() {
        return age;
    }

    public TestRequestBody setAge(Integer age) {
        this.age = age;
        return this;
    }
}
