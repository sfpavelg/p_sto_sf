package com.javamentor.qa.platform.dao.abstracts.dto.user;


import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;

import java.util.List;

public interface UserProfileTagDtoDao {

    List<UserProfileTagDto> getUserProfileTagDtoWithoutVotesByUserId(Long userId);

    List<Object []> getTagVotesByList(List<String> tagList);
}
