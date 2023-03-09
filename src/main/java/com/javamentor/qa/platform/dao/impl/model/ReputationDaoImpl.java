package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class ReputationDaoImpl extends ReadWriteDaoImpl<Reputation, Long> implements ReputationDao {

    private final EntityManager entityManager;

    public ReputationDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Optional<Reputation> getReputationByQuestionVoteSenderId(Long userId, Long questionId) {
        Query query = entityManager.createQuery("from Reputation where sender.id =:senderId" +
                "and question.id =: questionId", Reputation.class)
                .setParameter("senderId", userId)
                .setParameter("questionId", questionId);
        return SingleResultUtil.getSingleResultOrNull(query);

    }

    @Override
    public void deleteReputation(Long userId, Long questionId) {
        entityManager.createQuery("delete r from Reputation where sender.id =:senderId" +
                        "and question.id =:question.Id", Reputation.class)
                .setParameter("senderId", userId)
                .setParameter("questionId", questionId)
                .executeUpdate();

    }
}
