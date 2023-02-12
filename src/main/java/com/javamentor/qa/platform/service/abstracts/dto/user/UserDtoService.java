package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserDto;
import javassist.NotFoundException;

public interface UserDtoService {
    UserDto getById(Long id) throws NotFoundException;
}
