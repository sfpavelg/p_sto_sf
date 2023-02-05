package com.javamentor.qa.platform.webapp.controllers;

import com.javamentor.qa.platform.models.dto.UserDtoTest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/user/point")
@Api(value = "Тестовый контроллер")
public class TestPointController {

    private final Map<String, UserDtoTest> repository;

    public TestPointController(Map<String, UserDtoTest> repository) {
        this.repository = repository;
    }

    @ApiOperation(value = "тестовая операция")
    @PostMapping("{key}")
    public HttpStatus changePoints(
            @PathVariable @Parameter(description = "Идентификатор пользователя") String key,
            @RequestPart("point") @Parameter(description = "Количество баллов") Long point,
            @RequestPart("type") @Parameter(description = "Тип операции") String type
    ) {
        final UserDtoTest userDto = repository.get(key);
        userDto.setPoints(
                "plus".equalsIgnoreCase(type)
                        ? userDto.getPoints() + point
                        : userDto.getPoints() - point
        );
        return HttpStatus.OK;
    }

}
