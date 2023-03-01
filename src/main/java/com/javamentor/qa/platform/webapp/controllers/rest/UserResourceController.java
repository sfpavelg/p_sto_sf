package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(value = "User controller")
public class UserResourceController {

    private final UserDtoService userDtoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User with such id doesn't exist")})
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userDtoService.getById(id));
    }

    @GetMapping("/new")
    @ApiOperation(value = "Get all users by registration date and time (DESC). First shown the newest user",
            notes = "currentPageNumber is a number of page with dto's.", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Users don't exist")})
    public ResponseEntity<?> getAllUsersByPersistDateAndTime(@RequestParam(defaultValue = "0") int currentPageNumber,
                                         @RequestParam(defaultValue = "10") int itemsOnPage) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);

        return ResponseEntity.ok(userDtoService.getUsersByPersistDateTime(param));
    }
}
