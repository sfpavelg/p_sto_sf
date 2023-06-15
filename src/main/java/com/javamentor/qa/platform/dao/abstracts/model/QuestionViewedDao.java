package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;

import java.util.Optional;

public interface QuestionViewedDao extends ReadWriteDao<QuestionViewed, Long> {
    Optional<QuestionViewed> isExistQuestionViewByUserIdAndQuestion(Long userId, Question question);

}
