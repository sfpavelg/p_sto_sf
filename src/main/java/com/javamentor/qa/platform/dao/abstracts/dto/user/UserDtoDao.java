package com.javamentor.qa.platform.dao.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;
import java.util.Optional;

/**
 * A DTO for the {@link User} entity
 */
public interface UserDtoDao  {
    Optional<UserDto> getById(Long id);

    List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId);
    List<UserProfileQuestionDto> getAllUserRemovedQuestion(Long id);

    List<UserDto> getPageWithListTop10UsersAnswers();
}