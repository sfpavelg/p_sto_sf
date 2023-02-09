package com.javamentor.qa.platform.webapp.converters;

import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public abstract class QuestionConverter {
    public abstract Question questionCreateDtoToQuestion(QuestionCreateDto questionCreateDto);
}
