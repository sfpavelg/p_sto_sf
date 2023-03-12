package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ReputationDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class ReputationDaoImpl extends ReadWriteDaoImpl<Reputation, Long> implements ReputationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Reputation> getByQuestionAndUser(ReputationType type, Long questionId, Long senderId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("" +
                        "SELECT rep FROM Reputation rep " +
                        "WHERE rep.question.id = :questionId AND rep.sender.id = :senderId AND rep.type = :type", Reputation.class)
                .setParameter("type", type)
                .setParameter("questionId", questionId)
                .setParameter("senderId", senderId));
    }
}
