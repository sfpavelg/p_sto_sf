package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import javassist.NotFoundException;

public interface VoteQuestionService extends ReadWriteService<VoteQuestion, Long> {

    /**
     * Method for voting UP for a question. The result of this method is adding or updating {@link VoteQuestion} and {@link Reputation}
     *
     * @param questionId   id {@link Question} for vote
     * @param user         Security (authorized) user
     * @return {@link VoteQuestionDao#getSumUpDownVotes(Long) getSumUpDownVotes(questionId)} = (UpVoteQuestion - DownVoteQuestion)
     * @throws NotFoundException Question with questionId not found
     */
    Long voteUpForQuestion(Long questionId, User user) throws NotFoundException;

    /**
     * Method for voting DOWN for a question. The result of this method is adding or updating {@link VoteQuestion} and {@link Reputation}
     *
     * @param questionId   id {@link Question} for vote
     * @param user         Security (authorized) user
     * @return {@link VoteQuestionDao#getSumUpDownVotes(Long) getSumUpDownVotes(questionId)} = (UpVoteQuestion - DownVoteQuestion)
     * @throws NotFoundException Question with questionId not found
     */
    Long voteDownForQuestion(Long questionId, User user) throws NotFoundException;
}
