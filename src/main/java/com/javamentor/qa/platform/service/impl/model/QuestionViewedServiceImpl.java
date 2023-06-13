package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.QuestionViewedService;
import javassist.NotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.context.annotation.Lazy;


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
        if (!isViewedQuestionUser(questionViewed)) {
            super.persist(questionViewed);
        }
    }

    @Override
    @Cacheable(value="questionViewed", key="#questionViewed.user.id + '-' + #questionViewed.question.id")
    public boolean isViewedQuestionUser(QuestionViewed questionViewed) {
        return questionViewedDao.checkQuestionViewed(questionViewed).isPresent();
    }
}
