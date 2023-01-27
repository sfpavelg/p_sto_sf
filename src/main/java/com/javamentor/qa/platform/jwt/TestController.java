package com.javamentor.qa.platform.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/api/user/test")
    public String test1() {
        return "You has authority USER";
    }
}
