package com.javamentor.qa.platform.dao.abstracts.dto.user.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;

/**
 * Interface for returning PageDto<UserDto> sorted by votes on Questions and Answers
 */
public interface UserPageByVoteDto extends PageDtoDao<UserDto> {
}
