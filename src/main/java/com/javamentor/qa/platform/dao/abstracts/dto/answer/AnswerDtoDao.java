package com.javamentor.qa.platform.dao.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.answer.AnswerDto;

import java.util.List;
import java.util.Optional;

public interface AnswerDtoDao {
    List<AnswerDto> getAllByQuestionId(Long id);
}
