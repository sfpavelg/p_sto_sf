package com.javamentor.qa.platform.models.dto.user;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserProfileTagDto {

   private String tagName;

    private Long countVoteTag;

    private Long countAnswerQuestion;

    public UserProfileTagDto(String tagName) {
        this.tagName = tagName;
    }
}
