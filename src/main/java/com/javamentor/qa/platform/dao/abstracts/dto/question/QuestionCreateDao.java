package com.javamentor.qa.platform.dao.abstracts.dto.question;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;

public interface QuestionCreateDao extends ReadWriteDao <Question, Long> {
}
