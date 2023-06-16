package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
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
    @Cacheable(value="questionViewed", key="#questionViewed.user.id + '-' + #questionViewed.question.id")
    public void persist(QuestionViewed questionViewed) {
        if (questionViewedDao.isEmptyIsExistQuestionViewByUserIdAndQuestionId(questionViewed.getUser().getId(),
                questionViewed.getQuestion().getId())) {
            super.persist(questionViewed);
        }
    }
}
