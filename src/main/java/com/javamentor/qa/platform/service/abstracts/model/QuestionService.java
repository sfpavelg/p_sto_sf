package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import javassist.NotFoundException;


public interface QuestionService extends ReadWriteService<Question, Long> {
    QuestionDto addQuestion(Question question) throws NotFoundException;
}
