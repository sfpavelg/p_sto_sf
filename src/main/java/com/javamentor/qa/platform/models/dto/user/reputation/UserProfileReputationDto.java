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

    public UserProfileReputationDto(int countReputation, LocalDateTime persistDate, VoteType voteType, ReputationType reputationType) {
        this.countReputation = countReputation;
        this.persistDate = persistDate;
        this.action = ReputationHistoryType.getReputationHistoryType(voteType, reputationType);
    }
}
