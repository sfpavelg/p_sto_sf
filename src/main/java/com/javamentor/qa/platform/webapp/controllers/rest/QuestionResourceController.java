package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionCreateService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с Question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionCreateService questionCreateService;

    private final UserService userService;
    private ModelMapper mapper = new ModelMapper();

    public QuestionResourceController(QuestionDtoService questionDtoService, QuestionCreateService questionCreateService, UserService userService) {
        this.questionDtoService = questionDtoService;
        this.questionCreateService = questionCreateService;
        this.userService = userService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

    @PostMapping
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionCreateDto questionCreateDto) throws NotFoundException {
        Question question = mapper.map(questionCreateDto, Question.class);
        question.setUser(userService.getById(1L).get());
        questionCreateService.persist(question);
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(question.getId()));
    }
}
