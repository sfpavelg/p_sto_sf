package com.javamentor.qa.platform.models.dto.user;

import com.javamentor.qa.platform.models.dto.tag.UserTagFavoriteDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
public class UserProfileDto {

    private Long id;
    private Integer reputation;
    private Long countAnswer;
    private Long countQuestion;
    private Long countView;
    private List<UserTagFavoriteDto> userTagFavoriteDtoList;

    public UserProfileDto(Long id, Integer reputation, Long countAnswer, Long countQuestion, Long countView) {
        this.id = id;
        this.reputation = reputation;
        this.countAnswer = countAnswer;
        this.countQuestion = countQuestion;
        this.countView = countView;
    }
}
