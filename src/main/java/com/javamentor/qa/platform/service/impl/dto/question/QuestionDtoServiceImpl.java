package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;


@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao, TagDtoDao tagDtoDao) {
        this.questionDtoDao = questionDtoDao;
        this.tagDtoDao = tagDtoDao;
    }


    @Override
    public QuestionDto getQuestionDtoById(Long id) throws NotFoundException {

        if (questionDtoDao.getQuestionDtoById(id).isPresent()) {
            QuestionDto questionDto = questionDtoDao.getQuestionDtoById(id).get();
            questionDto.setListTagDto(tagDtoDao.getTagDtoById(id));
            return questionDto;
        } else {
            throw new NotFoundException("QuestionDto with id = " + id + " not found");
        }
    }
}
