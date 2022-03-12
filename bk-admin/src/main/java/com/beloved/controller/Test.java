package com.beloved.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Test {

    @GetMapping("/hell")
    public String test01() {
        return "hello bk";
    }

}
