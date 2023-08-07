package com.javamentor.qa.platform.models.dto.user.reputation;

import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserProfileReputationDto {
    private int countReputation;
    private LocalDateTime persistDate;
    private ReputationHistoryType action;

    public UserProfileReputationDto(int countReputation, LocalDateTime persistDate, VoteType voteType, ReputationType action) {
        this.countReputation = countReputation;
        this.persistDate = persistDate;
        if (action == ReputationType.VoteAnswer) {
            if (voteType == VoteType.UP_VOTE) {
                this.action = ReputationHistoryType.UP_VOTE;
            } else {
                this.action = ReputationHistoryType.DOWN_VOTE;
            }
        } else if (action == ReputationType.Question) {
            this.action = ReputationHistoryType.CREATE_QUESTION;
        }
    }
}
