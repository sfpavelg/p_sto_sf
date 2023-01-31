package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/example/user")
@Api("Тестовый контроллер для примера тестирования")
public class ExampleController {

    private final UserService userService;

    public ExampleController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента User по id")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        User user = userService.getById(id).orElse(null);
        return user == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("User with id = " + id + " not found") :
                ResponseEntity.ok(user);
    }
}
