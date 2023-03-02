package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;


public class VoteQuestionDaoImpl extends ReadWriteDaoImpl<VoteQuestion, Long> implements VoteQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VoteQuestion> getVoteByQuestionIdAndUserId(Long questionId, Long userId) {
        Query query = entityManager.createQuery("FROM VoteQuestion where user.id =: userId and question.id =: questionId", VoteQuestion.class)
                .setParameter("userId", userId)
                .setParameter("questionId", questionId);

        return SingleResultUtil.getSingleResultOrNull(query);

    }
}
