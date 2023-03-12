package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.VoteQuestion;

import java.util.Optional;

public interface VoteQuestionDao extends ReadWriteDao<VoteQuestion, Long> {
    Optional<VoteQuestion> getByUserId(Long questionId, Long userId);

    Long getSumUpDownVotes(Long questionId);
}
