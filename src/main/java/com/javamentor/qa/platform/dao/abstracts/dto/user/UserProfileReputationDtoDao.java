package com.javamentor.qa.platform.dao.abstracts.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto;

/**
 * Interface for returning PageDto<UserProfileReputationDto> sorted by persistDate
 */
public interface UserProfileReputationDtoDao extends PageDtoDao<UserProfileReputationDto> {
}
