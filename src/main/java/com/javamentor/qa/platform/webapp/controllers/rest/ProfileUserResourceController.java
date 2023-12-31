package com.javamentor.qa.platform.webapp.controllers.rest;

import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileVoteDto;
import com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.GroupBookmarkDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileCommentDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileQuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileReputationDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileTagDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileVoteDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.user.UserProfileDtoService;
import com.javamentor.qa.platform.service.abstracts.model.GroupBookmarkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user/profile")
@Api(value = "User profile controller")
public class ProfileUserResourceController {

    private final AnswerDtoService answerDtoService;
    private final UserProfileQuestionDtoService userProfileQuestionDtoService;
    private final GroupBookmarkDtoService groupBookmarkDtoService;
    private final GroupBookmarkService groupBookmarkService;
    private final UserProfileTagDtoService userProfileTagDtoService;
    private final UserProfileVoteDtoService userProfileVoteDtoService;
    private final UserProfileDtoService userProfileDtoService;
    private final UserProfileCommentDtoService userProfileCommentDtoService;
    private final UserProfileReputationDtoService userProfileReputationDtoService;

    @GetMapping("/questions")
    @ApiOperation(
            value = "Getting all UserProfileQuestionDto of authorized User",
            response = UserProfileQuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. List of UserProfileQuestionDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden"),
    })
    public ResponseEntity<?> getAllUserAuthorizedQuestions(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userProfileQuestionDtoService.getAllUserProfileQuestionDtoByUserId(user.getId()));
    }

    /**
     * Method return JSON with list all removed questions
     * @return {@link ResponseEntity} with status Ok and {@link List <UserProfileQuestionDto>} in body
     */
    @GetMapping("/delete/questions")
    @ApiOperation(
            value = "Getting all removed UserProfileQuestionDto",
            response = UserProfileQuestionDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserProfileQuestionDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity <?> getUserRemovedQuestion (@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userProfileQuestionDtoService.getAllUserRemovedQuestion(user.getId()));
    }

    @GetMapping("/question/week")
    @ApiOperation(
            value = "Getting the count of all user's answers by user ID per week")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. The count of all user's answers has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity<?> getAllAuthorizedUserAnswersPerWeek(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(answerDtoService.getCountAllAnswersPerWeekByUserId(user.getId()));
    }


    @PostMapping("/bookmark/group")
    @ApiOperation(
            value = "Add group for bookmarks in profile")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Group bookmark was successfully added"),
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity<?> addGroupOfBookmarks(@AuthenticationPrincipal User user,
                                                 @RequestBody String groupName) throws NotFoundException {
        GroupBookmark groupBookmark = groupBookmarkService.groupBookmarkPersist(user, groupName);
        return ResponseEntity.ok(groupBookmarkDtoService.getGroupBookmarkById(groupBookmark.getId()));
    }

    /**
     * Method return JSON with list of user group names
     * @return {@link ResponseEntity} with status Ok and {@link List <GroupBookmarkDto>} in body
     */
    @GetMapping("/bookmark/group")
    @ApiOperation(
            value = "Getting list of user group names",
            response = GroupBookmarkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserProfileQuestionDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity <?> getGroupBookmark (@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(groupBookmarkDtoService.getGroupBookmark(user.getId()));
    }


    /**
     * Method returns JSON with list of user tags with their count and votes
     * @return {@link ResponseEntity} with status Ok and {@link List <UserProfileTagDto>} in body
     */
    @GetMapping ("/tag")
    @ApiOperation(
            value = "Getting list of user tags with their count and votes",
            response = GroupBookmarkDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success request. UserProfileTagDto has been successfully returned"),
            @ApiResponse(code = 400, message = "Invalid password"),
            @ApiResponse(code = 403, message = "Forbidden")})
    public ResponseEntity <?> getUserTagsWithRating(@AuthenticationPrincipal User user){
        return ResponseEntity.ok(userProfileTagDtoService.getUserProfileTagDto(user.getId()));
    }

    @GetMapping("/vote")
    @ApiOperation(value = "Getting user profile votes info", response = UserProfileVoteDto.class)
    @ApiResponses(value = {
    @ApiResponse(code = 200, message = "Success request"),
    @ApiResponse(code = 401, message = "Unauthorized request"),
    @ApiResponse(code = 403, message = "Forbidden"),})
    public ResponseEntity<?> getUserProfileVoteDto(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(userProfileVoteDtoService.getUserProfileVoteDtoByUserId(user.getId()));
    }

    @GetMapping("{userId}")
    @ApiOperation(value = "Get user profile by id", response = UserProfileDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "User not found by this id")
    })
    public ResponseEntity<?> getUserProfileDto(@PathVariable Long userId) throws NotFoundException  {
        return ResponseEntity.ok(userProfileDtoService.getUserProfileDtoByUserId(userId));
    }

    @GetMapping("/comment")
    @ApiOperation(value = "Get UserProfileCommentDto for authorised user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden"),

    })
        public ResponseEntity<?> getUserProfileCommentDto
            (@AuthenticationPrincipal User user, @RequestParam(defaultValue = "1") int currentPageNumber,
             @RequestParam(defaultValue = "10") int itemsOnPage) {
        HashMap<String, Object> param = new HashMap<>();
        param.put("currentPageNumber", currentPageNumber);
        param.put("itemsOnPage", itemsOnPage);
        param.put("userId", user.getId());
        return ResponseEntity.ok(userProfileCommentDtoService.getUserProfileCommentDto(param));
    }
    @GetMapping("/reputation")
    @ApiOperation(value = "Get user reputation by items and currentPage", response = UserProfileReputationDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized request"),
            @ApiResponse(code = 403, message = "Forbidden")
    })
    public ResponseEntity<?> getUserProfileReputationDto(@RequestParam(value = "items", defaultValue = "10") int items,
                                                         @RequestParam(value = "currentPage", defaultValue = "1") int currentPage,
                                                         @AuthenticationPrincipal User user) {
        Map<String, Object> params = new HashMap<>();
        params.put("currentPageNumber", currentPage);
        params.put("itemsOnPage", items);
        params.put("userId", user.getId());
        return ResponseEntity.ok(userProfileReputationDtoService.getAllReputationByCurrentPage(params));
    }
}

