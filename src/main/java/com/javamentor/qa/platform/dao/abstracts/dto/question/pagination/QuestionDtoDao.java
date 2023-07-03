package com.javamentor.qa.platform.dao.abstracts.dto.question.pagination;

import com.javamentor.qa.platform.models.dto.question.QuestionDto;

import java.util.List;
import java.util.Optional;

public interface QuestionDtoDao {
    Optional<QuestionDto> getQuestionDtoById(Long id, Long userId);
    List<QuestionDto> getQuestionDtoByParameters(Long userId, List<String> text, List<String> tags, Long views, Long user, Long answers);

    Long getCountQuestionDto();
}
