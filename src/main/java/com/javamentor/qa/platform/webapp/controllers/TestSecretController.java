package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDtoTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("api/secret")
@Api(value = "секретный контроллер", description = "Позволяет удалить всех пользователей")
public class TestSecretController {

    private final Map<String, UserDtoTest> repository;

    public TestSecretController(Map<String, UserDtoTest> repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Тестовая операция")
    @GetMapping(value = "destroy")
    public HttpStatus destroy() {
        repository.clear();
        return HttpStatus.OK;
    }

}
