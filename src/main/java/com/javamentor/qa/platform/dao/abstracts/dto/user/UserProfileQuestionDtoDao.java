package com.javamentor.qa.platform.dao.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;

import java.util.List;

public interface UserProfileQuestionDtoDao {
    List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId);
}