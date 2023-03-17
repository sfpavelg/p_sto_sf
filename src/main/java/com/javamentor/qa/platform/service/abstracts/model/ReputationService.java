package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;

import java.util.Optional;

public interface ReputationService extends ReadWriteService<Reputation, Long>{
    Optional<Reputation> getByQuestionAndUser(ReputationType type, Long questionId, Long senderId);

}
