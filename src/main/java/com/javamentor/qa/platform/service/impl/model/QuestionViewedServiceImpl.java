package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import com.javamentor.qa.platform.service.abstracts.model.QuestionViewedService;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class QuestionViewedServiceImpl extends ReadWriteServiceImpl<QuestionViewed, Long> implements QuestionViewedService {

    private final QuestionViewedDao questionViewedDao;

    public QuestionViewedServiceImpl(QuestionViewedDao questionViewedDao) {
        super(questionViewedDao);
        this.questionViewedDao = questionViewedDao;
    }


    @Override
    @Transactional
    public void persist(QuestionViewed questionViewed) {
        if (!isEmptyIsExistQuestionViewByUserIdAndQuestion(questionViewed.getUser().getId(), questionViewed.getQuestion())) {
            super.persist(questionViewed);
        }
    }

    @Override
    @Cacheable(value="questionViewed", key="#userId + '-' + #question")
    public boolean isEmptyIsExistQuestionViewByUserIdAndQuestion(Long userId, Question question) {
        return questionViewedDao.isExistQuestionViewByUserIdAndQuestion(userId, question).isPresent();
    }
}
