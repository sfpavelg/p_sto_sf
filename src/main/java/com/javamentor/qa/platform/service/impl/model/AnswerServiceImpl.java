package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.AnswerDao;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import com.javamentor.qa.platform.service.abstracts.model.AnswerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl extends ReadWriteServiceImpl<Answer, Long> implements AnswerService {
    private final AnswerDao answerDao;

    public AnswerServiceImpl(AnswerDao answerDao) {
        super(answerDao);
        this.answerDao = answerDao;
    }

    @Transactional
    @Override
    public void deleteByUser(Long id, Long userId) {
        answerDao.deleteByUser(id, userId);
    }
}
