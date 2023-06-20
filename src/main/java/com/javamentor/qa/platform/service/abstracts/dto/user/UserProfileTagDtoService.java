package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;

import java.util.List;

public interface UserProfileTagDtoService {

    List<UserProfileTagDto> getUserProfileTagDto(Long userId);
}
