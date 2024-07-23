package com.hydroyura.prodms.archive.server.controllers;

import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/test")
public class TestController {

    @PostMapping(value = "/test1")
    public String test1(@RequestBody Optional<TestRequestBody> body) {
        int a = 1;
        return "TEST";
    }


}
