package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionDto;

import java.util.Optional;

public interface QuestionDtoService {
    Optional <QuestionDto> getQuestionDtoById(Long id);
}
