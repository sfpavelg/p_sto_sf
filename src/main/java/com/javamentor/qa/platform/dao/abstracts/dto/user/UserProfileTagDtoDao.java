package com.javamentor.qa.platform.dao.abstracts.dto.user;


import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;

import java.util.List;
import java.util.Map;

public interface UserProfileTagDtoDao {

    Map<String, Long> getUserProfileTagDtoWithoutVotesByUserId(Long userId);

    Map<String, Long> getTagVotesByList(Map<String, Long> tagList);
}
