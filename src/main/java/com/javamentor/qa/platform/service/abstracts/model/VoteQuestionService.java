package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import javassist.NotFoundException;

import java.util.Optional;

public interface VoteQuestionService extends ReadWriteService<VoteQuestion, Long> {
    Optional<VoteQuestion> getByUserId(Long questionId, Long userId);

    /**
     * Method for voting for a question. The result of this method is adding or updating {@link VoteQuestion} and {@link Reputation}
     *
     * @param questionId   id {@link Question} for vote
     * @param user         Security (authorized) user
     * @param voteType The type is determined based on the user's action
     * @return {@link VoteQuestionDao#getSumUpDownVotes(Long) getSumUpDownVotes(questionId)} = (UpVoteQuestion - DownVoteQuestion)
     * @throws NotFoundException Question with questionId not found
     */
    Long voteForQuestion(Long questionId, User user, VoteType voteType) throws NotFoundException;
}
