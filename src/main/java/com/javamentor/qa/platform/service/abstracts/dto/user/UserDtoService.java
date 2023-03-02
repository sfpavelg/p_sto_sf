package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface UserDtoService {
    UserDto getById(Long id) throws NotFoundException;

    /**
     * Получение пагинации всех пользователей отсортированных по голосам
     *
     * @param param - обязательные параметры (daoDtoImpl, itemsOnPage, currentPageNumber)
     * @author Шапедько Андрей
     * @since 02/03/2023
     */
    PageDto<UserDto> getAllUsersByVotes(HashMap<String, Object> param) throws NotFoundException;
}
