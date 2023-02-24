package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.webapp.converters.QuestionConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


import javax.validation.Valid;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с Question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionConverter questionConverter;
    private final QuestionService questionService;

    public QuestionResourceController(QuestionDtoService questionDtoService, QuestionConverter questionConverter, QuestionService questionService) {
        this.questionDtoService = questionDtoService;
        this.questionConverter = questionConverter;
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Получение элемента QuestionDto по id", response = QuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Question with such id doesn't exist")})
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

    @PostMapping
    @ApiOperation(value = "Добавление вопроса. Ожидает заполненный объект QuestionCreateDto", response = QuestionDto.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. Question added to DB and it's QuestionDto object returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Validation failed. Fields of QuestionCreateDto must be not empty or null")})
    public ResponseEntity<?> addQuestion(@Valid @RequestBody QuestionCreateDto questionCreateDto) throws NotFoundException {
        Question question = questionConverter.questionCreateDtoToQuestion(questionCreateDto, new User());
        questionService.persist(question);
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(question.getId()));
    }

    @PostMapping("/{questionId}/upVote")
    @ApiOperation(value = "api возвращает общее количество голосов, сумму up vote")
    @ApiResponses({
            @ApiResponse(code = 200, message = "голос За учтен"),
            @ApiResponse(code = 400, message = "Голос За не учтен"),
            @ApiResponse(code = 404, message = "Вопрос не найден")})
    public ResponseEntity<Long> upVote(@PathVariable("questionId") Long id,
                                       @AuthenticationPrincipal User user) {

        if (questionService.getById(id).isPresent()) {
            return ResponseEntity.ok(questionService.voteUpQuestion(user.getId(), id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{questionId}/downVote")
    @ApiOperation(value = "api возвращает общее количество голосов, сумму down vote")
    @ApiResponses({
            @ApiResponse(code = 200, message = "голос Против учтен"),
            @ApiResponse(code = 400, message = "Голос Против не учтен"),
            @ApiResponse(code = 404, message = "Вопрос не найден")})
    public ResponseEntity<Long> downVote(@PathVariable("questionId") Long id,
                                       @AuthenticationPrincipal User user) {

        if (questionService.getById(id).isPresent()) {
            return ResponseEntity.ok(questionService.voteDownQuestion(user.getId(), id));
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
