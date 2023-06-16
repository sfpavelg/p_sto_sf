package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class QuestionViewedDaoImpl extends ReadWriteDaoImpl<QuestionViewed, Long> implements QuestionViewedDao {
    @PersistenceContext
    private EntityManager entityManager;

    public boolean isEmptyIsExistQuestionViewByUserIdAndQuestionId(Long userId, Long questionId) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("" +
                        "SELECT qw FROM QuestionViewed qw " +
                        "WHERE qw.question.id = :questionId AND qw.user.id = :userId", QuestionViewed.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId))
                .isEmpty();
    }
}
