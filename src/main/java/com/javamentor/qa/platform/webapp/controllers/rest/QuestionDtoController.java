package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с QuestionDto")
public class QuestionDtoController {

    private final QuestionDtoService questionDtoService;

    public QuestionDtoController(QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) {
        QuestionDto questionDto = questionDtoService.getQuestionDtoById(id).orElse(null);
        return questionDto == null ?
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("QuestionDto with id = " + id + " not found") :
                ResponseEntity.ok(questionDto);
    }
}
