package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface QuestionDtoService {
    QuestionDto getQuestionDtoById(Long id) throws NotFoundException;

    PageDto<QuestionDto> getPageWithListQuestionDtoWithoutAnswer(HashMap<String, Object> param);

    PageDto<QuestionDto> getAllQuestionDto(HashMap<String, Object> param) throws NotFoundException;

    PageDto<QuestionDto> getPageWithListQuestionDtoSortedByNewest(HashMap<String, Object> param);
}
