package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.QuestionDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.TagDtoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoService tagDtoService;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao, TagDtoService tagDtoService) {
        this.questionDtoDao = questionDtoDao;
        this.tagDtoService = tagDtoService;
    }


    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id) {
        QuestionDto questionDto = questionDtoDao.getQuestionDtoById(id).orElse(null);
        if (questionDto != null) {
            questionDto.setListTagDto(tagDtoService.getTagDtoById(id).orElse(new ArrayList<>()));
        }
        return Optional.ofNullable(questionDto);
    }
}
