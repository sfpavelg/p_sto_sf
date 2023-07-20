package com.javamentor.qa.platform.service.abstracts.dto.user;


import com.javamentor.qa.platform.models.dto.user.UserProfileDto;
import javassist.NotFoundException;

public interface UserProfileDtoService {
    UserProfileDto getUserProfileDtoByUserId(Long userId) throws NotFoundException;
}
