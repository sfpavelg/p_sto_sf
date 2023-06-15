package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
@Api(value = "User controller")
public class UserResourceController {

    private final UserService userService;
    private final UserDtoService userDtoService;

    @PatchMapping("/changePassword")
    @ApiOperation(value = "Change user password")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. User password has been successfully changed"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity<?> changeUserPassword(@RequestBody(required = false) String userPassword) {
        userService.changeUserPassword(userPassword, (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get user", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User with such id doesn't exist")})
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) throws NotFoundException {
        return ResponseEntity.ok(userDtoService.getById(id));
    }

    /**
     * Method return JSON with list all users sorted by reputation, with pagination
     *
     * @param itemsCountOnPage The number of users per page. Optional parameter. The default value is 10
     * @param pageNumber       Page number of the page to be displayed (starts from zero)
     * @return {@link ResponseEntity} with status Ok and {@link PageDto<UserDto>} in body
     */
    @GetMapping("/reputation")
    @ApiOperation(value = "Get a page with a list of users sorted by reputation", response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto objects returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User with such id doesn't exist")})
    public ResponseEntity<?> getPageWithListUsersSortedByReputation(
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage
    ) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        return ResponseEntity.ok(userDtoService.getPageWithListUsersSortedByReputation(param));
    }

    @GetMapping("/new")
    @ApiOperation(value = "Get all users by registration date and time (DESC). First shown the newest user",
            notes = "currentPageNumber is a number of page with dto's.", response = UserDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Users don't exist")})
    public ResponseEntity<?> getAllUsersByPersistDateAndTime(@RequestParam(defaultValue = "1") int currentPageNumber,
                                                             @RequestParam(defaultValue = "10") int itemsOnPage) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);
        return ResponseEntity.ok(userDtoService.getUsersByPersistDateTime(param));
    }


    @GetMapping("/vote")
    @ApiOperation(
            value = "Getting pagination of users sorted by the number of votes in all questions and answers",
            response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. PageDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity<PageDto<UserDto>> getUsersSortedByVote(@RequestParam(value = "page", defaultValue = "1") int currentPage,
                                                                 @RequestParam(value = "items", defaultValue = "10") int items) throws NotFoundException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("currentPageNumber", currentPage);
        parameters.put("itemsOnPage", items);
        return ResponseEntity.ok(userDtoService.getAllUsersByVotes(parameters));
    }

    @GetMapping("/top10UsersAnswers")
    @ApiOperation(value = "List with top 10 users who gave the most answers to the question during the last week", response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})

    public ResponseEntity<?> getPageWithListTop10UsersAnswers() {
        return ResponseEntity.ok(userDtoService.getPageWithListTop10UsersAnswers());
    }

    //@GetMapping("/profile/vote")

}

