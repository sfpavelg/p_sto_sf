package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class VoteQuestionDaoImpl extends ReadWriteDaoImpl<VoteQuestion, Long> implements VoteQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;
    public Long getSumVoteUpAndDown(Long questionId) {
        return entityManager.createQuery("select count (v)" +
                        " from VoteQuestion v " +
                        "where v.question.id =:questionId AND v.vote = 'UP' AND v.vote = 'DOWN'", Long.class)
                .setParameter("questionId", questionId).getSingleResult();
    }
}
