package com.javamentor.qa.platform.models.dto.user.reputation;

import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;

public enum ReputationHistoryType {
    UP_VOTE(VoteType.UP_VOTE, ReputationType.VoteAnswer),
    DOWN_VOTE(VoteType.DOWN_VOTE, ReputationType.VoteAnswer),
    CREATE_QUESTION(null, ReputationType.Question);

    private final VoteType voteType;
    private final ReputationType reputationType;

    ReputationHistoryType(VoteType voteType, ReputationType reputationType) {
        this.voteType = voteType;
        this.reputationType = reputationType;
    }

    public static ReputationHistoryType getReputationHistoryType(VoteType voteType, ReputationType reputationType) {
        if (!(reputationType == ReputationType.Question || reputationType == ReputationType.VoteAnswer)) {
            return null;
        }
        for (ReputationHistoryType reputationHistoryType : ReputationHistoryType.values()) {
            if (reputationHistoryType.voteType == voteType && reputationHistoryType.reputationType == reputationType) {
                return reputationHistoryType;
            }
        }
        return null;
    }
}
