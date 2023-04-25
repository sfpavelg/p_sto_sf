package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.models.entity.question.CommentQuestion;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.BookmarkDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.CommentDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.*;
import com.javamentor.qa.platform.webapp.converters.QuestionConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user/question")
@Api("Question controller")
public class QuestionResourceController {

    private final QuestionDtoService questionDtoService;
    private final QuestionConverter questionConverter;
    private final QuestionService questionService;
    private final VoteQuestionService voteQuestionService;
    private final CommentDtoService commentDtoService;
    private final CommentQuestionService commentQuestionService;
    private final BookmarkService bookmarkService;
    private final BookmarkDtoService bookmarkDtoService;


    @GetMapping("/{id}")
    @ApiOperation(value = "Getting the QuestionDto element by id", response = QuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Question with such id doesn't exist")})
    public ResponseEntity<?> getQuestionDtoById(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(questionDtoService.getQuestionDtoById(id));
    }

    @PostMapping
    @ApiOperation(value = "Adding a question. Waits for a completed QuestionCreateDto object", response = QuestionDto.class, responseContainer = "List")
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

    /**
     * The method returns JSON with a page-by-page list of QuestionDTO objects for which no answer was given.
     *
     * @param pageNumber       Page number of the page to be displayed. The parameter must be greater than zero.
     * @param itemsCountOnPage Optional parameter. The number of items per page. The default value is 10.
     *                         The parameter must be greater than zero
     * @param trackedTag       Optional parameter, contains a list of ID tags of the {@link Tag} entity, for which it
     *                         is necessary to give a list of unanswered questions.
     * @param ignoredTag       Optional parameter, contains a list of ID tags of the {@link Tag} entity that should be
     *                         ignored when displaying a list of unanswered questions. If the question contains at least
     *                         one ignored tag, the question is not output.
     * @return {@link ResponseEntity} with status Ok and {@link PageDto<QuestionDto>} in body.
     */
    @GetMapping("/noAnswer")
    @ApiOperation(value = "Get a page with a list of QuestionDto without answers with filtering by " +
            "the passed tags in the request parameters", response = PageDto.class)
    public ResponseEntity<?> getPageWithListQuestionDtoWithoutAnswer(
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
            @RequestParam(value = "trackedTag", required = false) List<Long> trackedTag,
            @RequestParam(value = "ignoredTag", required = false) List<Long> ignoredTag
    ) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("trackedTag", trackedTag);
        param.put("ignoredTag", ignoredTag);
        return ResponseEntity.ok(questionDtoService.getPageWithListQuestionDtoWithoutAnswer(param));
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
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 404, message = "Incorrect id Question. Question with id not found")})
    public ResponseEntity<Long> voteUpForQuestion(@PathVariable Long questionId, @AuthenticationPrincipal User user) throws NotFoundException {
        return ResponseEntity.ok(voteQuestionService.voteUpForQuestion(questionId, user));
    }

    @PostMapping("/{questionId}/downVote")
    @ApiOperation(value = "Vote DOWN for question. Returned sum of all Up votes and Down votes.", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. Sum of UpVotes and DownVotes returned. VoteQuestion and Reputation added to DB."),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 404, message = "Incorrect id Question. Question with id not found")})
    public ResponseEntity<Long> voteDownForQuestion(@PathVariable Long questionId, @AuthenticationPrincipal User user) throws NotFoundException {
        return ResponseEntity.ok(voteQuestionService.voteDownForQuestion(questionId, user));
    }

    @GetMapping("/{questionId}/comments")
    @ApiOperation(value = "Getting all QuestionCommentDto by Question element id", response = QuestionCommentDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionCommentDto objects returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No question with such id")})
    public ResponseEntity<?> getAllCommentDtoByQuestionId(@PathVariable("questionId") Long questionId) throws NotFoundException {
        if (!questionService.existsById(questionId)) {
            return new ResponseEntity<>("No question with such id", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(commentDtoService.getAllCommentDtoByQuestionId(questionId));
    }

    /**
     * The method returns JSON with a paginated list of QuestionDTO objects sorted by popularity..
     *
     * @param pageNumber       Page number of the page to be displayed. The default value is 1.
     *                         The parameter must be greater than zero
     * @param itemsCountOnPage Optional parameter. The number of items per page. The default value is 10.
     *                         The parameter must be greater than zero
     * @param trackedTags      Optional parameter, contains a list of object tags {@link Tag} that defines preferred topics
     * @param ignoredTags      Optional parameter, contains a list of object ID tags {@link Tag} for which questions should be ignored.
     * @return {@link ResponseEntity} with status Ok and {@link PageDto<QuestionDto>} in body.
     */
    @GetMapping("/mostPopular")
    @ApiOperation(value = "Get a page with a list of QuestionDto sorted by popularity", response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Questions don't exist")})
    public ResponseEntity<?> getPageWithListMostPopularQuestionDto(
            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
            @RequestParam(value = "trackedTags", required = false) List<Long> trackedTags,
            @RequestParam(value = "ignoredTags", required = false) List<Long> ignoredTags)
            throws NotFoundException {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("trackedTags", trackedTags);
        param.put("ignoredTags", ignoredTags);
        return ResponseEntity.ok(questionDtoService.getPageWithListMostPopularQuestionDto(param));
    }

    /**
     * The method returns JSON with a paginated list of QuestionDTO objects, sorted by newest..
     *
     * @param pageNumber       Page number of the page to be displayed. The parameter must be greater than zero.
     * @param itemsCountOnPage Optional parameter. The number of items per page. The default value is 10.
     *                         The parameter must be greater than zero
     * @param trackedTag       Optional parameter, contains a list of ID tags of the {@link Tag} entity, for which it
     *                         is necessary to give a list of unanswered questions.
     * @param ignoredTag       Optional parameter, contains a list of ID tags of the {@link Tag} entity that should be
     *                         ignored when displaying a list of unanswered questions. If the question contains at least
     *                         one ignored tag, the question is not output.
     * @return {@link ResponseEntity} with status Ok and {@link PageDto<QuestionDto>} in body.
     */
    @GetMapping("/new")
    @ApiOperation(value = "Get all questionsDto sorted by newest",
            notes = "currentPageNumber is a number of page with dto's.", response = QuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. QuestionDto objects returned in response."),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Questions don't exist")})
    public ResponseEntity<?> getAllQuestionDtoSortedByNewest(
            @RequestParam(value = "page") Integer pageNumber,
            @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
            @RequestParam(value = "trackedTag", required = false) List<Long> trackedTag,
            @RequestParam(value = "ignoredTag", required = false) List<Long> ignoredTag
    ) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("trackedTag", trackedTag);
        param.put("ignoredTag", ignoredTag);
        return ResponseEntity.ok(questionDtoService.getPageWithListQuestionDtoSortedByNewest(param));
    }

    @PostMapping("/{questionId}/comment")
    @ApiOperation(value = "Add new comment for question by Question element id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Comment was successfully added to the question", response = QuestionCommentDto.class),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Question with the such ID was not found"),
    })
    public ResponseEntity<?> addCommentForQuestionById(@PathVariable Long questionId, @AuthenticationPrincipal User user, @RequestBody String text) {
        Optional<Question> question = questionService.getById(questionId);
        if (question.isEmpty()) {
            return new ResponseEntity<>("Question with the such ID was not found", HttpStatus.NOT_FOUND);
        }
        CommentQuestion commentQuestion = new CommentQuestion(text, user);
        commentQuestion.setQuestion(question.get());
        commentQuestionService.persist(commentQuestion);
        return ResponseEntity.ok(commentDtoService.getCommentById(commentQuestion.getComment().getId()));
    }
    /**
     * The method returns JSON with table fields containing bookmark id, question id and user id.
     *
     * @param questionId       Question id passed to the URL for adding a question to bookmarks.
     * @param user             Authenticated user logged in.
     * @return {@link ResponseEntity} with status Ok.
     */
    @PostMapping("/{questionId}/bookmark")
    @ApiOperation(value = "Add question to user bookmark.", response = Long.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Bookmark successfully added"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 404, message = "Incorrect id Question. Question with id not found")})
    public ResponseEntity<?> addQuestionToCurrentUserBookmark(@PathVariable("questionId") Long questionId,
                                                              @AuthenticationPrincipal User user) throws NotFoundException {
        BookMarks bookMark = bookmarkService.persistByQuestionId(questionId, user);
        return ResponseEntity.ok(bookmarkDtoService.getBookmarkById(bookMark.getId()));
    }
}
