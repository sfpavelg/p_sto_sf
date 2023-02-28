package com.javamentor.qa.platform.dao.abstracts.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Optional;

/**
 * A DTO for the {@link User} entity
 */
public interface UserDtoDao extends PageDtoDao<UserDto> {
    Optional<UserDto> getById(Long id);
}