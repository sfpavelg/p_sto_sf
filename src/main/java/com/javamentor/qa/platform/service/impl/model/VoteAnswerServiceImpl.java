package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.VoteAnswerService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class VoteAnswerServiceImpl extends ReadWriteServiceImpl<VoteAnswer, Long> implements VoteAnswerService {
    private final VoteAnswerDao voteAnswerDao;
    private final AnswerDao answerDao;
    private final ReputationDao reputationDao;


    public VoteAnswerServiceImpl(VoteAnswerDao voteAnswerDao, AnswerDao answerDao, ReputationDao reputationDao) {
        super(voteAnswerDao);
        this.voteAnswerDao = voteAnswerDao;
        this.answerDao = answerDao;
        this.reputationDao = reputationDao;
    }


    @Override
    @Transactional
    public Long voteForAnswer(Long answerId, User user, VoteType userVoteType) throws NotFoundException {
        Optional<VoteAnswer> optionalVote = voteAnswerDao.getByUserId(answerId, user.getId());
        VoteAnswer vote;
        if (optionalVote.isPresent()) {
            vote = optionalVote.get();

            if (!vote.getVote().equals(userVoteType)) {
                vote.setVote(userVoteType);
                this.update(vote);

                Reputation reputation = reputationDao.getByAnswerAndUser(ReputationType.VoteAnswer, answerId, user.getId()).get();
                reputation.setCount(userVoteType == VoteType.UP_VOTE ? +10 : -5);
                reputationDao.update(reputation);
            }
        } else {
            Optional<Answer> optionalAnswer = answerDao.getById(answerId);
            Answer answer;
            if (optionalAnswer.isPresent()) {
                answer = optionalAnswer.get();
            } else {
                throw new NotFoundException("Answer with id " + answerId + " not found.");
            }

            this.persist(new VoteAnswer(user, answer, userVoteType));
            reputationDao.persist(new Reputation(
                    answer.getUser(),
                    user,
                    userVoteType == VoteType.UP_VOTE ? +10 : -5,
                    ReputationType.VoteAnswer,
                    answer,
                    null
            ));
        }
        return voteAnswerDao.getSumUpDownVotes(answerId);
    }
}
