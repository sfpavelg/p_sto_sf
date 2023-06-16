package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileVoteDto;

public interface UserProfileVoteDtoService {

    UserProfileVoteDto getUserProfileVoteDtoByUserId(Long userId);
}
