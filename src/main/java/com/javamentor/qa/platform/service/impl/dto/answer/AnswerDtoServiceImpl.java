package com.javamentor.qa.platform.service.impl.dto.answer;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerDtoServiceImpl implements AnswerDtoService {
    private final AnswerDtoDao answerDtoDao;

    public AnswerDtoServiceImpl(AnswerDtoDao answerDtoDao) {
        this.answerDtoDao = answerDtoDao;
    }

    @Override
    public List<AnswerDto> getAllByQuestionId(Long id) throws NotFoundException {
        return answerDtoDao.getAllByQuestionId(id);
    }
}
