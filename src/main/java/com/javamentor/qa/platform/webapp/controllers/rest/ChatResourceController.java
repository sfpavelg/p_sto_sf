package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.models.entity.chat.ChatType;
import com.javamentor.qa.platform.service.abstracts.dto.chat.GroupChatDtoService;
import com.javamentor.qa.platform.models.dto.chat.SingleChatDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import com.javamentor.qa.platform.service.abstracts.dto.chat.SingleChatDtoService;
import com.javamentor.qa.platform.service.impl.model.SingleChatServiceImpl;
import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.chat.ChatDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/chat")
@Api("Chat controller")
public class ChatResourceController {

    private final MessageDtoService messageDtoService;
    private final SingleChatServiceImpl singleChatService;
    private final ChatDtoService chatDtoService;
    private final GroupChatService groupChatService;
    private final GroupChatDtoService groupChatDtoService;

    private final SingleChatDtoService singleChatDtoService;
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
    public ResponseEntity<?> getMessagesBySingleChatIdOrderNew(@RequestParam(value = "page", required = false, defaultValue = "1") Integer pageNumber,
                                                               @RequestParam(value = "items", required = false, defaultValue = "10") Integer itemsCountOnPage,
                                                               @PathVariable("id") Long chatId) {
        if (!singleChatService.existsById(chatId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", pageNumber);
        param.put("itemsOnPage", itemsCountOnPage);
        param.put("chatId", chatId);
        return ResponseEntity.ok(messageDtoService.getMessagesBySingleChatIdOrderNew(param));
    }


    @GetMapping
    @ApiOperation(value = "Поиск чатов по значению запроса - value для юзера", response = ChatDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password")})
    public ResponseEntity<?> getChatBySearch (@AuthenticationPrincipal User user,
                                              @RequestParam(value = "value", defaultValue = "") String value) {
        return ResponseEntity.ok(chatDtoService.getChatDtoByUserIdAndValue(user.getId(), value));
    }

    /**
     * Gets all single chat dtos.
     *
     *  @return A {@link ResponseEntity} containing a List of {@link SingleChatDto } objects, or a 404 response if no chats with the auth user.
     */
    @ApiOperation(value = "Get user's list SingleChatDto", response = SingleChatDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. SingleChatDto list returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    @GetMapping("/single")
    public ResponseEntity<List<SingleChatDto>> getSingleChatDto(@AuthenticationPrincipal User user) {
        return new ResponseEntity<>(singleChatDtoService.getSingleChatDto(user.getId()), HttpStatus.OK);
    }

    /**
     *
     * Method returns JSON of GroupChatDto with last message in the group chat
     *
     * */

    @ApiOperation(value = "Getting the GroupChatDto")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "GroupChatDto object returned in response"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Wrong link")})
    @GetMapping("/group")
    public ResponseEntity<?> getGroupChat() {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(groupChatDtoService.getGroupChatDto(authenticatedUser.getId(), ChatType.GROUP));
    }

    /**
     * Method deletes authorized user from the chat
     */
    @ApiOperation(value = "Deleting authorized user from the chat by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User has been successfully deleted from the chat"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Wrong chat id")})
    @DeleteMapping("/{chatId}")
    public ResponseEntity<?> deleteChatById(@PathVariable Long chatId) throws NotFoundException {
        User authenticatedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if  (groupChatService.existsById(chatId)) {
            groupChatService.deleteUserFromChatById(chatId, authenticatedUser.getId());
            return ResponseEntity.ok("Юзер удален из чата " + chatId);
        }
        if (singleChatService.existsById(chatId)) {
            singleChatService.deleteUserFromChatById(chatId, authenticatedUser.getId());
            return ResponseEntity.ok("Юзер удален из чата " + chatId);
        }
        return ResponseEntity.badRequest().body("Такого чата нет");
    }
}

