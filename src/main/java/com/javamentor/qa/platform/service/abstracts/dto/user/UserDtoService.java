package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface UserDtoService {
    UserDto getById(Long id) throws NotFoundException;
    PageDto<UserDto> getUsersByPersistDateTime(HashMap<String, Object> param) throws NotFoundException;
    PageDto<UserDto> getAllUsersByVotes(HashMap<String, Object> param) throws NotFoundException;
}
