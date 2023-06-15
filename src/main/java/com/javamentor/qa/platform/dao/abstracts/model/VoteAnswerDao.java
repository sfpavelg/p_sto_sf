package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;

import java.util.List;
import java.util.Optional;

public interface VoteAnswerDao extends ReadWriteDao<VoteAnswer, Long> {
    Optional<VoteAnswer> getByUserId(Long answerId, Long userId);

    Long getCountVotesLastMonth(Long userId);

    Long getSumUpDownVotes(Long answerId);

    List<VoteAnswer> getAllVotesUpOrDownByUserId(Long userId, VoteType vote);
}
