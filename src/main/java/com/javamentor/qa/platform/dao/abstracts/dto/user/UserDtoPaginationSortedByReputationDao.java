package com.javamentor.qa.platform.dao.abstracts.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserDto;

/**
 * A DTO for pagination list of the {@link UserDto} entity sorted by reputation
 */
public interface UserDtoPaginationSortedByReputationDao extends PageDtoDao<UserDto> {
}
