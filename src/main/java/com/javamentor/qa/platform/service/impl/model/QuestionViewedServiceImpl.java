package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionViewedDao;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import com.javamentor.qa.platform.service.abstracts.model.QuestionViewedService;
import org.springframework.stereotype.Service;

@Service
public class QuestionViewedServiceImpl extends ReadWriteServiceImpl<QuestionViewed, Long> implements QuestionViewedService {
    public QuestionViewedServiceImpl(QuestionViewedDao questionViewedDao) {
        super(questionViewedDao);
    }
}
