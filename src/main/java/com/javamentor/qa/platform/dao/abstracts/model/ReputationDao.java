package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;

import java.util.Optional;

public interface ReputationDao extends ReadWriteDao<Reputation, Long> {

    Optional<Reputation> getReputationByAnswerAndUser(Long answerId, Long userId);
    Optional<Reputation> getReputationByVoteQuestion(Long userId, Long questionId);
}

