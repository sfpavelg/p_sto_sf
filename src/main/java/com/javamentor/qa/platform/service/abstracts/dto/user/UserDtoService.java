package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface UserDtoService {
    UserDto getById(Long id) throws NotFoundException;

    PageDto<UserDto> getPageWithListUsersSortedByReputation(HashMap<String, Object> param);
}
