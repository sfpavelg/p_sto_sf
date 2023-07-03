package com.javamentor.qa.platform.models.dto.user;

import com.javamentor.qa.platform.models.dto.tag.UserTagFavoriteDto;

import java.util.List;

public class UserProfileDto {

    private Long id;
    private Integer reputation;
    private Long countAnswer;
    private Long countQuestion;
    private Long countView;
    private List<UserTagFavoriteDto> userTagFavoriteDtoList;
}
