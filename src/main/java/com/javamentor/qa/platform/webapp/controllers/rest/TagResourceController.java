package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.tag.IgnoredTagService;
import com.javamentor.qa.platform.service.abstracts.model.tag.TrackedTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/user/tag")
@Api("Контроллер для работы с Tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;
    private final IgnoredTagService ignoredTagService;

    private final TrackedTagService trackedTagService;

    public TagResourceController(TagDtoService tagDtoService, IgnoredTagService ignoredTagService, TrackedTagService trackedTagService) {
        this.tagDtoService = tagDtoService;
        this.ignoredTagService = ignoredTagService;
        this.trackedTagService = trackedTagService;
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

    @PostMapping("/{id}/ignored")
    @ApiOperation(value = "Добавление Tag в Ignored. Возвращает TagDto", response = TagDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. TagDto returned"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Tag with such id doesn't exist")})
    public ResponseEntity<?> addIgnoredTag(@PathVariable Long id) throws NotFoundException {
        ignoredTagService.persistByTagId(id);
        return ResponseEntity.ok(tagDtoService.getById(id));
    }

    @PostMapping("/{id}/tracked")
    @ApiOperation(value = "Добавление Tag в Tracked. Возвращает TagDto", response = TagDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. TagDto returned"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Tag with such id doesn't exist")})
    public ResponseEntity<?> addTrackedTag(@PathVariable Long id) throws NotFoundException {
        trackedTagService.persistByTagId(id);
        return ResponseEntity.ok(tagDtoService.getById(id));
    }
}
