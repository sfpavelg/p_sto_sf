package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import com.javamentor.qa.platform.service.abstracts.model.ReputationService;
import org.springframework.stereotype.Service;

@Service
public class ReputationServiceImpl extends ReadWriteServiceImpl<Reputation, Long> implements ReputationService {

    private final ReputationDao reputationDao;

    public ReputationServiceImpl(ReputationDao reputationDao) {
        super(reputationDao);
        this.reputationDao = reputationDao;
    }

    @Override
    public void persist(Reputation reputation) {
        super.persist(reputation);
    }

    @Override
    public Reputation increaseReputationByQuestionVoteUp(Question question, User voteSender) {
        Reputation reputation = new Reputation();
        reputation.setType(ReputationType.VoteQuestion);
        reputation.setQuestion(question);
        reputation.setAuthor(question.getUser());
        reputation.setSender(voteSender);
        reputation.setCount(+10);

        return reputation;
    }

    @Override
    public Reputation decreaseReputationByQuestionVoteDown(Question question, User voteSender) {
        Reputation reputation = new Reputation();
        reputation.setType(ReputationType.VoteQuestion);
        reputation.setQuestion(question);
        reputation.setAuthor(question.getUser());
        reputation.setSender(voteSender);
        reputation.setCount(-5);

        return reputation;
    }

    @Override
    public Reputation deleteReputationByQuestionVoteUp(Question question, User voteSender) {
        Reputation reputation = new Reputation();
        reputation.setType(ReputationType.VoteQuestion);
        reputation.setQuestion(question);
        reputation.setAuthor(question.getUser());
        reputation.setSender(voteSender);
        reputation.setCount(-10);

        return reputation;
    }

    @Override
    public Reputation deleteReputationByQuestionVoteDown(Question question, User voteSender) {
        Reputation reputation = new Reputation();
        reputation.setType(ReputationType.VoteQuestion);
        reputation.setQuestion(question);
        reputation.setAuthor(question.getUser());
        reputation.setSender(voteSender);
        reputation.setCount(+5);

        return reputation;

    }

}
