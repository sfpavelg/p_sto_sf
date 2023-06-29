package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import javassist.NotFoundException;

import java.util.HashMap;
import java.util.List;


public interface QuestionDtoService {
    QuestionDto getQuestionDtoById(Long id, Long userId) throws NotFoundException;

    PageDto<QuestionDto> getPageWithListQuestionDtoByTag(HashMap<String, Object> param);

}
