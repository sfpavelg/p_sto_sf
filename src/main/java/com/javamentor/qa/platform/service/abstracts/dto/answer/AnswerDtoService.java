package com.javamentor.qa.platform.service.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import javassist.NotFoundException;

import java.util.HashMap;
import java.util.List;

public interface AnswerDtoService {
    List<AnswerDto> getAllByQuestionId(Long id) throws NotFoundException;

    Long getCountAllAnswersPerWeekByUserId(Long userId);

    List<AnswerDto> getAllDeletedAnswersByUserId(Long userId);

    PageDto<AnswerDto> getAllAnswersByVotes(HashMap<String, Object> param) throws NotFoundException;
}
