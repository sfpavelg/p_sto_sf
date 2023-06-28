package com.javamentor.qa.platform.models.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileTagDto {

    private String tagName;

    private Long countVoteTag;

    private Long countAnswerQuestion;

    public UserProfileTagDto(String tagName, Long countAnswerQuestion) {
        this.tagName = tagName;
        this.countAnswerQuestion = countAnswerQuestion;
    }
}
