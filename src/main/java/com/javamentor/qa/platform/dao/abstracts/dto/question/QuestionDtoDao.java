package com.javamentor.qa.platform.dao.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;


import java.util.Optional;

public interface QuestionDtoDao {
    Optional<QuestionViewDto> getQuestionDtoById(Long id);
}
