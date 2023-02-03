package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionCreateDao;
import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionCreateService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class QuestionCreateServiceImpl extends ReadWriteServiceImpl <Question, Long> implements QuestionCreateService {

    private final QuestionCreateDao questionCreateDao;

    public QuestionCreateServiceImpl(QuestionCreateDao questionCreateDao) {
        super(questionCreateDao);
        this.questionCreateDao = questionCreateDao;
    }

}
