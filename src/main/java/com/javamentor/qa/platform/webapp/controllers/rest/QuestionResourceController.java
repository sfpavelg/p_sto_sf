package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionCreateService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.TagService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javassist.NotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с Question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionCreateService questionCreateService;

    private final QuestionService questionService;

    private final UserService userService;

    private final TagService tagService;
    private ModelMapper mapper = new ModelMapper();

    public QuestionResourceController(QuestionDtoService questionDtoService, QuestionCreateService questionCreateService, QuestionService questionService, UserService userService, TagService tagService) {
        this.questionDtoService = questionDtoService;
        this.questionCreateService = questionCreateService;
        this.questionService = questionService;
        this.userService = userService;
        this.tagService = tagService;
    }


    @GetMapping("/{id}")
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

    @PostMapping
    @ApiOperation("Получение элемента QuestionDto по id")
    public ResponseEntity<?> addQuestion(@RequestBody QuestionCreateDto questionCreateDto) throws NotFoundException {
        if (questionCreateDto.getTitle() == null || questionCreateDto.getDescription() == null) {
            throw new NullPointerException("Fields must be not empty");
        }
        Question question = mapper.map(questionCreateDto, Question.class);
        List<Tag> tagListDto = question.getTags();
        Map<String, Long> map = tagService.getAllTagNamesAndIds();
        for (Tag t:tagListDto) {
            if (map.containsKey(t.getName())) {
                Long id = map.get(t.getName());
                tagListDto.set(tagListDto.indexOf(t), tagService.getById(id).get());
            }
            else {
                tagService.persist(t);
            }
        }
        question.setTags(tagListDto);
        //TODO set User from security when feature is ready
        if (userService.getById(1L).isPresent()) {
            question.setUser(userService.getById(1L).get());
        }
        else {
            question.setUser(userService.getById(100L).get());
        }
        questionService.persist(question);
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(question.getId()));
    }
}
