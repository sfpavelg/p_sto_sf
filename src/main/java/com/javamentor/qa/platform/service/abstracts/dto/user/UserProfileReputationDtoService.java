package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto;

import java.util.Map;

public interface UserProfileReputationDtoService {
    PageDto<UserProfileReputationDto> getAllReputationByCurrentPage(Map<String, Object> param);
}
