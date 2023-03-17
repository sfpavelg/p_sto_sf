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
    @Transactional
    public Long voteUpForQuestion(Long questionId, User user) throws NotFoundException {
        processVoteForQuestion(questionId, user, VoteType.UP_VOTE);
        return voteQuestionDao.getSumUpDownVotes(questionId);
    }

    @Override
    @Transactional
    public Long voteDownForQuestion(Long questionId, User user) throws NotFoundException {
        processVoteForQuestion(questionId, user, VoteType.DOWN_VOTE);
        return voteQuestionDao.getSumUpDownVotes(questionId);
    }

    private void processVoteForQuestion(Long questionId, User user, VoteType userVoteType) throws NotFoundException {
        Optional<VoteQuestion> optionalVote = voteQuestionDao.getByUserId(questionId, user.getId());
        if (optionalVote.isEmpty()) {
            persistVoteQuestionWithReputation(questionId, user, userVoteType);
        }
        if (optionalVote.isPresent()) {
            updateVoteQuestionWithReputation(optionalVote.get(), questionId, user, userVoteType);
        }
    }

    private void updateVoteQuestionWithReputation(VoteQuestion vote, Long questionId, User user, VoteType userVoteType) {
        if (!vote.getVote().equals(userVoteType)) {
            vote.setVote(userVoteType);
            this.update(vote);

            Reputation reputation = reputationService.getByQuestionAndUser(ReputationType.VoteQuestion, questionId, user.getId()).get();
            reputation.setCount(userVoteType == VoteType.UP_VOTE ? +10 : -5);
            reputationService.update(reputation);
        }
    }

    private void persistVoteQuestionWithReputation(Long questionId, User user, VoteType userVoteType) throws NotFoundException {
        Optional<Question> optionalQuestion = questionService.getById(questionId);
        if (optionalQuestion.isEmpty()) {
            throw new NotFoundException("Question with id " + questionId + " not found.");
        }

        Question question = optionalQuestion.get();
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
}
