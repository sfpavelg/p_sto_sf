package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QuestionDaoImpl extends ReadWriteDaoImpl<Question, Long> implements QuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Long getSumVoteUp(Long questionId) {
        return entityManager.createQuery("select" +
                " count(v) " +
                "from VoteQuestion v" +
                " where v.question.id =:questionId and v.vote = 'UP'", Long.class)
                .setParameter("questionId", questionId).getSingleResult();
    }

    @Override
    public Long getSumVoteDown(Long questionId) {
        return entityManager.createQuery("select" +
                        " count(v) " +
                        "from VoteQuestion v" +
                        " where v.question.id =:questionId and v.vote = 'DOWN'", Long.class)
                .setParameter("questionId", questionId).getSingleResult();
    }
}
