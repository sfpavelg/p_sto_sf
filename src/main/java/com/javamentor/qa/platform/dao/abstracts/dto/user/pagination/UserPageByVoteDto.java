package com.javamentor.qa.platform.dao.abstracts.dto.user.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;

/**
 * Интерфейс для возвращения PageDto<UserDto> отсортированных по количеству голосов в вопросах и ответах
 * @author Шапедько Андрей
 * @since 02/03/2023
 */
public interface UserPageByVoteDto extends PageDtoDao<UserDto> {
}
