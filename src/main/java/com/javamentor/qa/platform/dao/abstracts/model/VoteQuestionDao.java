package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;

import java.util.List;
import java.util.Optional;

public interface VoteQuestionDao extends ReadWriteDao<VoteQuestion, Long> {
    Optional<VoteQuestion> getByUserId(Long questionId, Long userId);

    List<VoteQuestion> getAllVotesUpOrDownByUserId(Long userId, VoteType vote);

    /**
     * Method that find all VotesQuestion and return sum of it.
     * @param questionId Id of {@link com.javamentor.qa.platform.models.entity.question.Question Question}
     * @return Long result = UpVotesQuestion - DownVotesQuestion
     */
    Long getSumUpDownVotes(Long questionId);

    Long getAllVotesLastMonth(Long userId);
}
