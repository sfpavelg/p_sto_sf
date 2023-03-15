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
    public Long voteForAnswer(Long answerId, User user, VoteType voteType) throws NotFoundException {

        Optional<Answer> optionalAnswer = answerDao.getById(answerId);
        Answer answer;
        if (!optionalAnswer.isPresent()) {
            throw new NotFoundException("Answer with id " + answerId + " not found.");
        }
        answer = optionalAnswer.get();
        return addVoteAnswerAndAddReputation(answerId, answer, user, voteType);
    }

    @Override
    @Transactional
    public Long addVoteAnswerAndAddReputation(Long answerId, Answer answer, User user, VoteType voteType) {
        Optional<VoteAnswer> optionalVote = voteAnswerDao.getByUserId(answerId, user.getId());
        VoteAnswer vote;
        if (!optionalVote.isPresent()) {
            this.persist(new VoteAnswer(user, answer, voteType));
            reputationDao.persist(new Reputation(
                    answer.getUser(),
                    user,
                    voteType == VoteType.UP_VOTE ? +10 : -5,
                    ReputationType.VoteAnswer,
                    answer,
                    null
            ));
            return voteAnswerDao.getSumUpDownVotes(answerId);
        }else {
            vote = optionalVote.get();
            return updateVoteAnswerAndUpdateReputation(vote, answerId, user, voteType);
        }
    }

    @Override
    @Transactional
    public Long updateVoteAnswerAndUpdateReputation(VoteAnswer vote, Long answerId, User user, VoteType voteType) {

        vote.setVote(voteType);
        this.update(vote);

        Reputation reputation = reputationDao.getByAnswerAndUser(ReputationType.VoteAnswer, answerId, user.getId()).get();
        reputation.setCount(voteType == VoteType.UP_VOTE ? +10 : -5);
        reputationDao.update(reputation);

        return voteAnswerDao.getSumUpDownVotes(answerId);
    }
}
