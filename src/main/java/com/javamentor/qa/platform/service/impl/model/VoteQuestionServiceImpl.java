package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteQuestionServiceImpl extends ReadWriteServiceImpl<VoteQuestion, Long> implements VoteQuestionService {

    private final VoteQuestionDao voteQuestionDao;
    private final QuestionService questionService;
    private final ReputationService reputationService;

    public VoteQuestionServiceImpl(VoteQuestionDao voteQuestionDao, QuestionService questionService, ReputationService reputationService) {
        super(voteQuestionDao);
        this.voteQuestionDao = voteQuestionDao;
        this.questionService = questionService;
        this.reputationService = reputationService;
    }

    @Override
    public Optional<VoteQuestion> getByUserId(Long questionId, Long userId) {
        return voteQuestionDao.getByUserId(questionId, userId);
    }

    /**
     * Method for voting for a question. The result of this method is adding or updating {@link VoteQuestion} and {@link Reputation}
     *
     * @param questionId   - {@link Question} for vote
     * @param user         - Security (authorized) user
     * @param userVoteType - The type is determined based on the user's action
     * @return {@link VoteQuestionDao#getSumUpDownVotes(Long) getSumUpDownVotes(questionId)} = (UpVoteQuestion - DownVoteQuestion)
     * @throws NotFoundException - Question with questionId not found
     */
    @Override
    @Transactional
    public Long voteForQuestion(Long questionId, User user, VoteType userVoteType) throws NotFoundException {
        Optional<VoteQuestion> optionalVote = getByUserId(questionId, user.getId());
        VoteQuestion vote;
        if (optionalVote.isPresent()) {
            vote = optionalVote.get();

            if (!vote.getVote().equals(userVoteType)) {
                vote.setVote(userVoteType);
                this.update(vote);

                Reputation reputation = reputationService.getByQuestionAndUser(ReputationType.VoteQuestion, questionId, user.getId()).get();
                reputation.setCount(userVoteType == VoteType.UP_VOTE ? +10 : -5);
                reputationService.update(reputation);
            }
        } else {
            Optional<Question> optionalQuestion = questionService.getById(questionId);
            Question question;
            if (optionalQuestion.isPresent()) {
                question = optionalQuestion.get();
            } else {
                throw new NotFoundException("Question with id " + questionId + " not found.");
            }

            this.persist(new VoteQuestion(user, question, userVoteType));
            reputationService.persist(new Reputation(
                    question.getUser(),
                    user,
                    userVoteType == VoteType.UP_VOTE ? +10 : -5,
                    ReputationType.VoteQuestion,
                    question,
                    null
            ));
        }

        return voteQuestionDao.getSumUpDownVotes(questionId);
    }
}
