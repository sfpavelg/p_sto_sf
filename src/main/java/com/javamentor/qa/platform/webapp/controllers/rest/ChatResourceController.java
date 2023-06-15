package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import com.javamentor.qa.platform.service.impl.model.SingleChatServiceImpl;
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
@Api("Chat controller")
public class ChatResourceController {


    private final MessageDtoService messageDtoService;
    private final SingleChatServiceImpl singleChatService;

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
        if(!singleChatService.existsById(chatId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("chatId", chatId);
        return ResponseEntity.ok(messageDtoService.getAllSingleChatMessagesSortedByPersistDate(param));
    }
}
