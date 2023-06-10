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
    private QuestionViewed questionViewed;

    public QuestionViewedServiceImpl(QuestionViewedDao questionViewedDao) {
        super(questionViewedDao);
        this.questionViewedDao = questionViewedDao;
    }

    @Override
    @Transactional
    public void persistViewQuestionByUser(User user, Question question) throws NotFoundException {
        if (!isViewedQuestionUser(user, question)){
            persist(questionViewed);
        }
    }

    @Override
    @Cacheable(value="questionViewed")
    public boolean isViewedQuestionUser(User user, Question question) throws NotFoundException {
        questionViewed = new QuestionViewed();
        questionViewed.setQuestion(question);
        questionViewed.setUser(user);
        return questionViewedDao.getQuestionViewByUser(questionViewed).isPresent();
    }
}
