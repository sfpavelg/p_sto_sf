package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import com.javamentor.qa.platform.service.abstracts.model.MessageService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user/chat")
@Api("Question controller")
public class ChatResourceController {


    private final MessageService messageService;
    private final MessageDtoService messageDtoService;
    private final UserService userService;

    /**
     * Gets a MessageDto by its id.
     *
     * @param messageId The id of the message to get.
     * @return A {@link ResponseEntity} containing a {@link MessageDto } object, or a 404 response if no message with the given id exists.
     */
    @GetMapping("/{id}/single/message")
    @ApiOperation(value = "Get message by id", response = MessageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. MessageDto object returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No message with such id")})
    public ResponseEntity<?> getMessageDtoById(@PathVariable("id") Long messageId) {
        if (!messageService.existsById(messageId)) {
            return new ResponseEntity<>("No question with such id", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(messageDtoService.getMessageDtoById(messageId));
    }

    /**
     Gets all messages for a user by their id.
     @param userId The id of the user to get messages for.
     @return A {@link ResponseEntity} containing a list of {@link MessageDto } objects, or a 404 response if no user with the given id exists.
     */
    @GetMapping("/{id}/user/message")
    @ApiOperation(value = "Get message by id", response = MessageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. List of massages object returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No user with such id")})
    public ResponseEntity<?> getAllUserMessagesById(@PathVariable("id") Long userId) {
        if (!userService.existsById(userId)) {
            return new ResponseEntity<>("No user with such id", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(messageDtoService.getAllUserMessagesById(userId));
    }


//    @GetMapping("/{questionId}/comments")
//    @ApiOperation(value = "Getting all QuestionCommentDto by Question element id", response = QuestionCommentDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success request. QuestionCommentDto objects returned in response (May be empty list)"),
//            @ApiResponse(code = 401, message = "Unauthorized request"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "No question with such id")})
//    public ResponseEntity<?> getAllCommentDtoByQuestionId(@PathVariable("questionId") Long questionId) throws NotFoundException {
//        if (!questionService.existsById(questionId)) {
//            return new ResponseEntity<>("No question with such id", HttpStatus.NOT_FOUND);
//        }
//        return ResponseEntity.ok(commentDtoService.getAllCommentDtoByQuestionId(questionId));
//    }

//    /**
//     * The method returns JSON with a paginated list of QuestionDTO objects sorted by popularity..
//     *
//     * @param pageNumber       Page number of the page to be displayed. The default value is 1.
//     *                         The parameter must be greater than zero
//     * @param itemsCountOnPage Optional parameter. The number of items per page. The default value is 10.
//     *                         The parameter must be greater than zero
//     * @param trackedTags      Optional parameter, contains a list of object tags {@link Tag} that defines preferred topics
//     * @param ignoredTags      Optional parameter, contains a list of object ID tags {@link Tag} for which questions should be ignored.
//     * @return {@link ResponseEntity} with status Ok and {@link PageDto < QuestionViewDto >} in body.
//     */
//    @GetMapping("/mostPopular")
//    @ApiOperation(value = "Get a page with a list of QuestionDto sorted by popularity", response = PageDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Questions don't exist")})
//    public ResponseEntity<?> getPageWithListMostPopularQuestionDto(
//            @RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
//            @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
//            @RequestParam(value = "trackedTags", required = false) List<Long> trackedTags,
//            @RequestParam(value = "ignoredTags", required = false) List<Long> ignoredTags)
//            throws NotFoundException {
//        HashMap<String, Object> param = new HashMap<>();
//        param.put("currentPageNumber", pageNumber);
//        param.put("itemsOnPage", itemsCountOnPage);
//        param.put("trackedTags", trackedTags);
//        param.put("ignoredTags", ignoredTags);
//        return ResponseEntity.ok(questionViewDtoService.getPageWithListMostPopularQuestionDto(param));
//    }


//    @GetMapping()
//    @ApiOperation(value = "Получение всех элементов AnswerDto по id элемента Question", response = AnswerDto.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success request. AnswerDto objects returned in response (May be empty list)"),
//            @ApiResponse(code = 401, message = "Unauthorized request"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "No question with such id")})
//    public  getAllByQuestionId(@PathVariable("questionId") Long questionId) throws NotFoundException {
//
//
//

    //        if (!questionService.existsById(questionId)) {
//            return new ResponseEntity<>("No question with such id", HttpStatus.NOT_FOUND);
//        }
//        return new ResponseEntity<>(answerDtoService.getAllByQuestionId(questionId), HttpStatus.OK);
//    }
}
