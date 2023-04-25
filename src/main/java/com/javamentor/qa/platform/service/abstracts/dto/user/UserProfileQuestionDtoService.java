package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;

import java.util.List;

public interface UserProfileQuestionDtoService {
    
    List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId);
    List<UserProfileQuestionDto> getAllUserRemovedQuestion(Long id);
}