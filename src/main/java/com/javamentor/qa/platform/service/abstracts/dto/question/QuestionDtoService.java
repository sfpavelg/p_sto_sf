package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import javassist.NotFoundException;

import java.security.Principal;


public interface QuestionDtoService {
    QuestionDto getQuestionDtoById(Long id) throws NotFoundException;

    QuestionDto addQuestion(QuestionCreateDto questionCreateDto, Principal principal) throws NotFoundException;
}
