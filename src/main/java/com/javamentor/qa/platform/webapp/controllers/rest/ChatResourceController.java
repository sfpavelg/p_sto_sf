package com.javamentor.qa.platform.webapp.controllers.rest;


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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/chat")
@Api(value = "User chat controller")
public class ChatResourceController {

    private final ChatDtoService chatDtoService;

    @GetMapping("/{value}")
    @ApiOperation(value = "Поиск чатов по значению запроса - value для юзера", response = ChatDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 400, message = "Invalid password")})
    public ResponseEntity<?> getChatBySearch (@PathVariable("value") String value,
                                              @AuthenticationPrincipal User user) throws NotFoundException {
        if (value == null || value.equals("")) {
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(chatDtoService.getAllUserChatsByValue(user, value));
    }
}
