package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteAnswerDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;


@Repository
public class VoteAnswerDaoImpl extends ReadWriteDaoImpl<VoteAnswer, Long> implements VoteAnswerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VoteAnswer> getByUserId(Long answerId, Long userId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("SELECT va FROM VoteAnswer va " +
                        "WHERE va.user.id = :userId AND va.answer.id = :answerId", VoteAnswer.class)
                .setParameter("userId", userId)
                .setParameter("answerId", answerId));
    }

    @Override
    public Long getSumUpDownVotes(Long answerId) {
        return entityManager.createQuery("SELECT (coalesce(count(vaUp),0) - coalesce((" +
                        "       SELECT count(vaDown) FROM VoteAnswer vaDown " +
                        "       WHERE vaDown.answer.id = :answerId  AND vaDown.vote = :voteDown),0)) " +
                        "FROM VoteAnswer vaUp " +
                        "WHERE vaUp.answer.id = :answerId  AND vaUp.vote = :voteUp", Long.class)
                .setParameter("voteUp", VoteType.UP_VOTE)
                .setParameter("voteDown", VoteType.DOWN_VOTE)
                .setParameter("answerId", answerId)
                .getSingleResult();
    }
}
