package com.javamentor.qa.platform.dao.abstracts.dto.user;


import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;

import java.util.List;
import java.util.Map;

public interface UserProfileTagDtoDao {

    List<UserProfileTagDto> getUserProfileTagDtoWithoutVotesByUserId(Long userId);

    Map<String, Long> getTagVotesFromQuestionsByList (List<String> tagNames);

    Map<String, Long> getTagVotesFromAnswersByList (List<String> tagNames);
}
