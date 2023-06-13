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
import java.util.HashMap;


@RestController
@AllArgsConstructor
@RequestMapping("/api/user/chat")
@Api("Question controller")
public class ChatResourceController {


    private final MessageService messageService;
    private final MessageDtoService messageDtoService;
    private final UserService userService;

    /**
     * Gets all single chat MessageDto sorted by persist date.
     *
     * @param chatId The id of the chat to get.
     * @return A {@link ResponseEntity} containing a List of {@link MessageDto } objects sorted by date, or a 404 response if no message with the given id exists.
     */
    @GetMapping("/{id}/single/message")
    @ApiOperation(value = "Get single chat messages by id", response = MessageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. MessageDto object returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No chat with such id")})
    public ResponseEntity<?> getAllSingleChatMessagesSortedByPersistDate(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
                                                                         @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
                                                                         @PathVariable("id") Long chatId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("chatId", chatId);
        return ResponseEntity.ok(messageDtoService.getAllSingleChatMessagesSortedByPersistDate(param));
    }

    /**
     * Gets all users MessageDto by its id.
     *
     * @param userId The id of the user to get.
     * @return A {@link ResponseEntity} containing a List of {@link MessageDto } objects sorted by date, or a 404 response if no message with the given id exists.
     */
    @GetMapping("/{id}/all/message")
    @ApiOperation(value = "Get message by id", response = MessageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. MessageDto object returned in response (May be empty list)"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No user with such id")})
    public ResponseEntity<?> getAllUserMessageDtoSortedByDate(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
                                                              @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
                                                              @PathVariable("id") Long userId) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("userId", userId);
        return ResponseEntity.ok(messageDtoService.getAllUserMessageDtoSortedByDate(param));
    }

    /**
     * Gets a MessageDto by its id.
     *
     * @param messageId The id of the message to get.
     * @return A {@link ResponseEntity} containing a {@link MessageDto } object, or a 404 response if no message with the given id exists.
     */
    @GetMapping("/message/{id}/")
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
     * Gets all messages for a user by their id.
     *
     * @param userId The id of the user to get messages for.
     * @return A {@link ResponseEntity} containing a list of {@link MessageDto } objects, or a 404 response if no user with the given id exists.
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
}
