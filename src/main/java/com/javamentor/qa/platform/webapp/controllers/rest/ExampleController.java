package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/example/user")
@Api("Тестовый контроллер для примера тестирования")
public class ExampleController {

    private final UserDtoService userDtoService;

    public ExampleController(UserDtoService userDtoService) {
        this.userDtoService = userDtoService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента User по id")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto userDto = userDtoService.getUserDtoById(id).orElse(null);
        return userDto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + id + " not found") :
                ResponseEntity.ok(userDto);
    }
}
