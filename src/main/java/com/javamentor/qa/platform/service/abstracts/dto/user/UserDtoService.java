package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface UserDtoService {
    UserDto getById(Long id) throws NotFoundException;

    /**
     * @return sorted Page<UserDto> sorted by votes on Questions and Answers
     * @param param required parameters (daoDtoImpl, itemsOnPage, currentPageNumber)
     */
    PageDto<UserDto> getAllUsersByVotes(HashMap<String, Object> param) throws NotFoundException;
}
