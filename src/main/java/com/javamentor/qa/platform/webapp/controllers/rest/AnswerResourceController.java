package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/user/question/{questionId}/answer")
@Api("Контроллер для работы с Answer")
public class AnswerResourceController {
    private final AnswerDtoService answerDtoService;
    private final AnswerService answerService;

    private final QuestionService questionService;

    public AnswerResourceController(AnswerDtoService answerDtoService, AnswerService answerService, QuestionService questionService) {
        this.answerDtoService = answerDtoService;
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping()
    @ApiOperation(value = "Получение всех элементов AnswerDto по id элемента Question", response = AnswerDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. AnswerDto objects returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No question with such id")})
    public ResponseEntity<?> getAllByQuestionId(@PathVariable("questionId") Long questionId) throws NotFoundException {
        if (!questionService.existsById(questionId)) {
            return new ResponseEntity<>("No question with such id", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(answerDtoService.getAllByQuestionId(questionId), HttpStatus.OK);
    }



    @ApiOperation(value = "Удаление ответа")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Запрос успешно выполнен"),
            @ApiResponse(code = 400, message = "Ответ с указанным уникальным идентификатором (Id) не найден")
    })
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId) {
        if (answerService.getById(answerId).isPresent()) {
            answerService.deleteById(answerId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}


