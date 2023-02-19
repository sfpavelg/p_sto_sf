package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user/question/{questionId}/answer")
@Api("Контроллер для работы с Answer")
public class ResourceAnswerController {
    private final AnswerService answerService;

    public ResourceAnswerController(AnswerService answerService) {
        this.answerService = answerService;
    }


    @ApiOperation(value = "Удаление ответа ")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Ответ с указанным уникальным идентификатором (Id) не найден")
    })
    @DeleteMapping("/{answerId}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Long answerId,
                                             @AuthenticationPrincipal User user) {
        if (answerService.getById(answerId).isPresent()) {
            answerService.deleteByUser(answerId, user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }
}


