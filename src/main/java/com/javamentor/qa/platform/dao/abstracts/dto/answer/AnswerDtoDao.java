package com.javamentor.qa.platform.dao.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.answer.AnswerDto;

import java.util.List;
import java.util.Map;


public interface AnswerDtoDao {
    List<AnswerDto> getAllByQuestionId(Long id);

    Long getCountPerWeekByUserId(Long userId);

    Map<Long, List<AnswerDto>> getAnswersMapByQuestionId(List<Long> qId);

    List<AnswerDto> getAllByQuestionIdSortedByUsefulAndCount(Long id);
}
