package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDtoTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Успешное извлечение пользовательских данных",response = UserDtoTest.class),
            @ApiResponse(code = 404, message = "Пользователь не найден",response = UserDtoTest.class),
            @ApiResponse(code = 500, message = "Внутренняя ощибка сервера",response = UserDtoTest.class)}
    )
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

    @ApiOperation(value = "тестовая операция")
    @PostMapping("{key}")
    public HttpStatus changePoints(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String key,
            @RequestParam("point") @Parameter(description = "Количество баллов") Long point,
            @RequestParam("type") @Parameter(description = "Тип операции") String type
    ) {
        final UserDtoTest userDto = repository.get(key);
        userDto.setPoints(
                "plus".equalsIgnoreCase(type)
                        ? userDto.getPoints() + point
                        : userDto.getPoints() - point
        );
        return HttpStatus.OK;
    }

    @ApiOperation(value = "Тестовая операция")
    @GetMapping(value = "destroy")
    public HttpStatus destroy() {
        repository.clear();
        return HttpStatus.OK;
    }

}



