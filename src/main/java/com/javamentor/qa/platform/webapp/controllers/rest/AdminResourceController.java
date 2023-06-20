package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/admin")
public class AdminResourceController {

    private AnswerDtoService answerDtoService;
    private UserService userService;

    @GetMapping("/answer/delete")
    @ApiOperation(value = "Getting all deleted user's answers", response = AnswerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. AnswerDto objects returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No user with such id")})
    public ResponseEntity<?> getAllDeletedAnswersByUserId(@RequestParam Long userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<>("No user with such id", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(answerDtoService.getAllDeletedAnswersByUserId(userId));
    }
}
