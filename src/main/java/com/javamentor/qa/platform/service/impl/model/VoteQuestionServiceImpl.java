package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import com.javamentor.qa.platform.service.abstracts.model.VoteQuestionService;
import org.springframework.stereotype.Service;


@Service
public class VoteQuestionServiceImpl extends ReadWriteServiceImpl<VoteQuestion, Long> implements VoteQuestionService {

    private final QuestionService questionService;
    private final UserService userService;
    private final VoteQuestionDao voteQuestionDao;

    public VoteQuestionServiceImpl(ReadWriteDao<VoteQuestion, Long> readWriteDao, QuestionService questionService, UserService userService, VoteQuestionDao voteQuestionDao) {
        super(readWriteDao);
        this.questionService = questionService;
        this.userService = userService;
        this.voteQuestionDao = voteQuestionDao;
    }



    @Override
    public Long voteUpQuestion(Long userId, Long questionId) {
        voteQuestionDao.persist(new VoteQuestion(userService.getById(userId).get(), questionService.getById(questionId).get(), VoteType.UP_VOTE));
        Reputation reputation = new Reputation();
        reputation.setCount(reputation.getCount()+10);
        super.persist(reputation);

        return getSumVoteUpAndDown(questionId);

    }

    @Override
    public Long voteDownQuestion(Long userId, Long questionId) {
        voteQuestionDao.persist(new VoteQuestion(userService.getById(userId).get(), questionService.getById(questionId).get(), VoteType.DOWN_VOTE));
        Reputation reputation = new Reputation();
        reputation.setCount(reputation.getCount()-5);
        super.persist(reputation);

        return getSumVoteUpAndDown(questionId);

    }

    @Override
    public Long getSumVoteUpAndDown(Long questionId) {
        return voteQuestionDao.getSumVoteUpAndDown(questionId);
    }
}

