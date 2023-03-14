package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.abstracts.model.tag.IgnoredTagService;
import com.javamentor.qa.platform.service.abstracts.model.tag.TrackedTagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user/tag")
@Api("Контроллер для работы с Tag")
public class TagResourceController {

    private final TagDtoService tagDtoService;
    private final IgnoredTagService ignoredTagService;

    private final TrackedTagService trackedTagService;

    private final UserService userService;

    public TagResourceController(TagDtoService tagDtoService, IgnoredTagService ignoredTagService, TrackedTagService trackedTagService, UserService userService) {
        this.tagDtoService = tagDtoService;
        this.ignoredTagService = ignoredTagService;
        this.trackedTagService = trackedTagService;
        this.userService = userService;
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
            @ApiResponse(code = 400, message = "User already has such tag in Ignored or Tracked"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Tag with such id doesn't exist")})
    public ResponseEntity<?> addIgnoredTag(@PathVariable Long id) throws NotFoundException {
        ignoredTagService.persistByTagId(id);
        Optional<TagDto> tagDto = tagDtoService.getById(id);
        return tagDto.isPresent() ? ResponseEntity.ok(tagDto.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping("/{id}/tracked")
    @ApiOperation(value = "Добавление Tag в Tracked. Возвращает TagDto", response = TagDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. TagDto returned"),
            @ApiResponse(code = 400, message = "User already has such tag in Tracked or Ignored"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Tag with such id doesn't exist")})
    public ResponseEntity<?> addTrackedTag(@PathVariable Long id) throws NotFoundException {
        trackedTagService.persistByTagId(id);
        Optional<TagDto> tagDto = tagDtoService.getById(id);
        return tagDto.isPresent() ? ResponseEntity.ok(tagDto.get()) : ResponseEntity.notFound().build();
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

    /**
     * The method returns a JSON with a list of all TrackedTags of the authorized user
     * without a filled description field in the list of returned TagDto objects
     *
     * @return {@link ResponseEntity} with status Ok and {@link List}<{@link TagDto}> in body without a
     * filled in "description" field.
     */
    @GetMapping("/tracked")
    @ApiOperation(value = "Getting all TrackedTags of an authorized user", response = List.class)
    public ResponseEntity<List<TagDto>> getAllTrackedTagAuthenticatedUser(@AuthenticationPrincipal UserDetails authenticatedUser) {
        Long idAuthenticatedUser = userService.getByEmail(authenticatedUser.getUsername()).orElseThrow().getId();
        return ResponseEntity.ok(tagDtoService.getTrackedTagsByUserId(idAuthenticatedUser));
    }

}
