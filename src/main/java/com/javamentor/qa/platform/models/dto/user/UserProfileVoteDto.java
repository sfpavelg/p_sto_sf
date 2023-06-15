package com.javamentor.qa.platform.models.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserProfileVoteDto {

    private Long countVoteUp;
    private Long countVoteDown;
    private Long countVoteQuestion;
    private Long countVoteAnswer;
    private Long countVoteMonth;
}
