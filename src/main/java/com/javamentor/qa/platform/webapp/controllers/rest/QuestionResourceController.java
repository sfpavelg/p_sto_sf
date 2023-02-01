package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с Question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;

    public QuestionResourceController(QuestionDtoService questionDtoService) {
        this.questionDtoService = questionDtoService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }
}
