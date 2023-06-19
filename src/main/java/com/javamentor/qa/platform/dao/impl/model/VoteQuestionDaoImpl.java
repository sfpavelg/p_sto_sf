package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.VoteQuestionDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class VoteQuestionDaoImpl extends ReadWriteDaoImpl<VoteQuestion, Long> implements VoteQuestionDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VoteQuestion> getByUserId(Long questionId, Long userId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("" +
                        "SELECT vq FROM VoteQuestion vq " +
                        "WHERE vq.user.id = :userId AND vq.question.id = :questionId", VoteQuestion.class)
                .setParameter("userId", userId)
                .setParameter("questionId", questionId));
    }



    @Override
    public Long getSumUpDownVotes(Long questionId) {
        return entityManager.createQuery("" +
                        "SELECT (coalesce(count(vqUp),0) - coalesce((" +
                        "       SELECT count(vqDown) FROM VoteQuestion vqDown " +
                        "       WHERE vqDown.question.id = :questionId  AND vqDown.vote = :voteDown),0)) " +
                        "FROM VoteQuestion vqUp " +
                        "WHERE vqUp.question.id = :questionId  AND vqUp.vote = :voteUp", Long.class)
                .setParameter("voteUp", VoteType.UP_VOTE)
                .setParameter("voteDown", VoteType.DOWN_VOTE)
                .setParameter("questionId", questionId)
                .getSingleResult();
    }
}
