package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user/tag")
@Api("Контроллер для работы с Tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;

    public TagResourceController(TagDtoService tagDtoService) {
        this.tagDtoService = tagDtoService;
    }

    @GetMapping("/related")
    @ApiOperation(value = "Получение списка ТОП-10 тэгов", response = List.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Success request. QuestionDto object returned in response"),
//            @ApiResponse(code = 401, message = "Unauthorized request"),
//            @ApiResponse(code = 403, message = "Forbidden"),
//            @ApiResponse(code = 404, message = "Question with such id doesn't exist")})
    public ResponseEntity<?> getRelatedTagsDtoList() {
        return ResponseEntity.ok(tagDtoService.getRelatedTagsDto());
    }
}
