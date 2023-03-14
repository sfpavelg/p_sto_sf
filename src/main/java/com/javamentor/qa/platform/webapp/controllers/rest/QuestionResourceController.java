package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import com.javamentor.qa.platform.webapp.converters.QuestionConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/user/question")
@Api("Контроллер для работы с Question")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionConverter questionConverter;
    private final QuestionService questionService;
    private final VoteQuestionService voteQuestionService;

    public QuestionResourceController(QuestionDtoService questionDtoService, QuestionConverter questionConverter, QuestionService questionService, VoteQuestionService voteQuestionService) {
        this.questionDtoService = questionDtoService;
        this.questionConverter = questionConverter;
        this.questionService = questionService;
        this.voteQuestionService = voteQuestionService;
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

    @GetMapping()
    @ApiOperation(value = "Get all questionDto",
            notes = "currentPageNumber is a number of page with dto's.", response = QuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Questions don't exist")})
    public ResponseEntity<?> getAllQuestionDto(@RequestParam(defaultValue = "1") int currentPageNumber,
                                               @RequestParam(defaultValue = "10") int itemsOnPage,
                                               @RequestParam(required = false) List<Long> trackedTags,
                                               @RequestParam(required = false) List<Long> ignoredTags) throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);
        param.put("trackedTags", trackedTags);
        param.put("ignoredTags", ignoredTags);
        return ResponseEntity.ok(questionDtoService.getAllQuestionDto(param));
    }
    @PostMapping("/{questionId}/upVote")
    @ApiOperation(value = "Vote UP for question. Returned sum of all Up votes and Down votes.", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. Sum of UpVotes and DownVotes returned. VoteQuestion and Reputation added to DB."),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password")})
    public ResponseEntity<Long> voteUpForQuestion(@PathVariable Long questionId, @AuthenticationPrincipal User user) throws NotFoundException {
        return ResponseEntity.ok(voteQuestionService.voteForQuestion(questionId, user, VoteType.UP_VOTE));
    }

    @PostMapping("/{questionId}/downVote")
    @ApiOperation(value = "Vote DOWN for question. Returned sum of all Up votes and Down votes.", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. Sum of UpVotes and DownVotes returned. VoteQuestion and Reputation added to DB."),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password")})
    public ResponseEntity<Long> voteDownForQuestion(@PathVariable Long questionId, @AuthenticationPrincipal User user) throws NotFoundException {
        return ResponseEntity.ok(voteQuestionService.voteForQuestion(questionId, user, VoteType.DOWN_VOTE));
    }
}
