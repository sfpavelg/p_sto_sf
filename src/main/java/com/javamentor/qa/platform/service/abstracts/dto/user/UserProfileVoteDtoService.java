package com.javamentor.qa.platform.service.abstracts.dto.user;

import com.javamentor.qa.platform.models.dto.user.UserProfileVoteDto;

public interface UserProfileVoteDtoService {


    Long getQuestionsCountVoteUpOrDown(Long userId, Boolean upTrueDownFalse);

    Long getAnswersCountVoteUpOrDown(Long userId, Boolean upTrueDownFalse);

    UserProfileVoteDto getUserProfileVoteDtoByUserId(Long userId);
}
