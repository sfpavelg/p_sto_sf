package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import javassist.NotFoundException;


public interface QuestionDtoService {
    QuestionDto getQuestionDtoById(Long id) throws NotFoundException;

}
