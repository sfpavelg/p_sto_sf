package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface UserProfileQuestionDtoService {
    
    List<UserProfileQuestionDto> getAllUserAuthorizedQuestions(User user);
}