package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;

public interface ReputationService extends ReadWriteService<Reputation, Long> {
    Reputation increaseReputationByQuestionVoteUp(Question question, User voteSender);
    Reputation decreaseReputationByQuestionVoteDown(Question question, User voteSender);
    void deleteReputation(Long questionId, Long userId);


}
