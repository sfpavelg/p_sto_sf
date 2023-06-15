package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import com.javamentor.qa.platform.models.entity.user.reputation.Reputation;
import com.javamentor.qa.platform.models.entity.user.reputation.ReputationType;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class QuestionViewedDaoImpl extends ReadWriteDaoImpl<QuestionViewed, Long> implements QuestionViewedDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<QuestionViewed> isExistQuestionViewByUserIdAndQuestion(Long userId, Question question) {
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery("" +
                        "SELECT qw FROM QuestionViewed qw " +
                        "WHERE qw.question.id = :questionId AND qw.user.id = :userId", QuestionViewed.class)
                .setParameter("questionId", question.getId())
                .setParameter("userId", userId));
    }
}
