package com.javamentor.qa.platform.service.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import javassist.NotFoundException;

import java.util.List;
import java.util.Optional;

public interface AnswerDtoService {
    List<AnswerDto> getAllByQuestionId(Long id) throws NotFoundException;
}
