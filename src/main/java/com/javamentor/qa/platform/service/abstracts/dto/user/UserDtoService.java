package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserDto;

import java.util.Optional;

public interface UserDtoService {
    Optional<UserDto> getUserDtoById(Long id);
}
