package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(value = "User controller")
public class UserResourceController {

    private final UserDtoService userDtoService;

    private final UserService userService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User with such id doesn't exist")})
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userDtoService.getById(id));
    }

    @GetMapping("/vote")
    @ApiOperation(
            value = "Получение пагинации пользователей отсортированных по количеству голосов во всех вопросах и ответах",
            response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Запрос успешно выполнен. Возвращен объект PageDto<UserDto>"),
            @ApiResponse(code = 403, message = "Нет доступа"),
            @ApiResponse(code = 404, message = "Запрашиваемая страница не найдена / находится вне диапазона всех страниц")})
    public ResponseEntity<PageDto<UserDto>> getUsersSortedByVote(@RequestParam(value = "page", defaultValue = "0") int currentPage,
                                                                 @RequestParam(value = "items", defaultValue = "10") int items) throws NotFoundException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("currentPageNumber", currentPage);
        parameters.put("itemsOnPage", items);
        return ResponseEntity.ok(userDtoService.getAllUsersByVotes(parameters));
    }
}
