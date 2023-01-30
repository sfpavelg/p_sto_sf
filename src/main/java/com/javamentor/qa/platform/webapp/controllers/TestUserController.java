package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDtoTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping("/api/user")
@Api(value = "Тестовый контроллер")
public class TestUserController {

    private final Map<String, UserDtoTest> repository;


    public TestUserController(Map<String, UserDtoTest> repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "Регистрация пользователей",response = UserDtoTest.class, notes = "Позволяет зарегистрировать пользователя")
    @PutMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus registerUser(@RequestBody UserDtoTest userDtoTest) {
        repository.put(userDtoTest.getKey(), userDtoTest);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Обновляет пользователя", response = UserDtoTest.class, notes = "Позволяет обновить пользователя")
    @PostMapping(produces = APPLICATION_JSON_VALUE)
    public HttpStatus updateUser(@RequestBody UserDtoTest userDtoTest) {
        if (!repository.containsKey(userDtoTest.getKey())) return HttpStatus.NOT_FOUND;
        repository.put(userDtoTest.getKey(), userDtoTest);
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Тестовая операция")
    @GetMapping(value = "{key}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDtoTest> getSimpleDto(@PathVariable("key") String key) {
        return ResponseEntity.ok(repository.get(key));
    }

}



