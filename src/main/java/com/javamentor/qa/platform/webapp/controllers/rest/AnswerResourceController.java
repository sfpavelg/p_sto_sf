package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/answer")
@Api("Контроллер для работы с Answer")
public class AnswerResourceController {

    private final AnswerDtoService answerDtoService;

    @ApiOperation(
            value = "Getting pagination of answers sorted by the number of votes",
            response = PageDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. PageDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    @GetMapping("/popular")
    public ResponseEntity<PageDto<AnswerDto>> getMostPopularAnswersByVotes(@RequestParam(value = "items", defaultValue = "10") int items,
                                                                               @RequestParam(value = "currentPage", defaultValue = "1") int currentPage) throws NotFoundException {
        HashMap<String, Object> parameters = new HashMap<>();
        parameters.put("currentPageNumber", currentPage);
        parameters.put("itemsOnPage", items);
        return ResponseEntity.ok(answerDtoService.getAllAnswersByVotes(parameters));
    }
}
