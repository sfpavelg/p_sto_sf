package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface QuestionDtoService {
    QuestionViewDto getQuestionDtoById(Long id) throws NotFoundException;

    PageDto<QuestionViewDto> getPageWithListQuestionDtoWithoutAnswer(HashMap<String, Object> param);

    PageDto<QuestionViewDto> getAllQuestionDto(HashMap<String, Object> param) throws NotFoundException;

    PageDto<QuestionDto> getPageWithListMostPopularQuestionForMonthDto(HashMap<String, Object> param) throws NotFoundException;
    PageDto<QuestionViewDto> getPageWithListQuestionDtoSortedByNewest(HashMap<String, Object> param);
    PageDto<QuestionViewDto> getPageWithListMostPopularQuestionDto(HashMap<String, Object> param)throws NotFoundException;
}
