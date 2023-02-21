package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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
    @ApiOperation(value = "Получение списка самых популярных тэгов (использующихся на наибольшем количестве вопросов, макс. 10 штук)", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. List of RelatedTagDto returned"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "No tags in DB yet")})
    public ResponseEntity<?> getRelatedTagsDtoList() {
        return ResponseEntity.ok(tagDtoService.getRelatedTagsDto());
    }


    @GetMapping("/ignored")
    @ApiOperation(value = "Получение списка игнорируемых тэгов авторизованного пользователя", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. List of IgnoredTag returned"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity<?> getAllUserIgnoredTag()  {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(tagDtoService.getIgnoredTagByUserId(user.getId()));
    }


}
