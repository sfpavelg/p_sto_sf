package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionCreateDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionCreateDaoImpl extends ReadWriteDaoImpl <Question, Long> implements QuestionCreateDao {
}
