package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Question with such id doesn't exist")})
    public ResponseEntity<?> getUserById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(userDtoService.getById(id));
    }
}
