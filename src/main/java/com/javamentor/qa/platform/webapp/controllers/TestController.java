package com.javamentor.qa.platform.webapp.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
@Api(value = "Тестовый контроллер")
public class TestController {

    @ApiOperation(value = "Тестовая операция", notes = "Подробное описание операции.")
    @GetMapping("/test")
    public String helloPage() {
        return "/test";
    }

    @ApiOperation(value = "Тестовая операция", notes = "Подробное описание операции.")
    @PostMapping
    public String create() {
        return "redirect/test.html";
    }

    @ApiOperation(value = "Тестовая операция", notes = "Подробное описание операции.")
    @PatchMapping("/{id}")
    public String update() {
        return "redirect:/hello";
    }

    @ApiOperation(value = "Тестовая операция", notes = "Подробное описание операции.")
    @DeleteMapping("/{id}")
    public String delete() {
        return "redirect:/hello";
    }
}



