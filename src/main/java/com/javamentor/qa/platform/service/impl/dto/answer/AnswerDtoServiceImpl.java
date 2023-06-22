package com.javamentor.qa.platform.service.impl.dto.answer;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.answer.AnswerDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AnswerDtoServiceImpl extends PageDtoService<AnswerDto> implements AnswerDtoService {
    private final AnswerDtoDao answerDtoDao;

    public AnswerDtoServiceImpl(Map<String, PageDtoDao<AnswerDto>> beansMap, AnswerDtoDao answerDtoDao) {
        super(beansMap);
        this.answerDtoDao = answerDtoDao;
    }

    @Override
    public List<AnswerDto> getAllByQuestionId(Long id) throws NotFoundException {
        return answerDtoDao.getAllByQuestionId(id);
    }

    @Override
    public Long getCountAllAnswersPerWeekByUserId(Long userId) {
        return answerDtoDao.getCountPerWeekByUserId(userId);
    }

    @Override
    public List<AnswerDto> getAllDeletedAnswersByUserId(Long userId) {
        return answerDtoDao.getAllDeletedAnswersByUserId(userId);
    }
    @Override
    public PageDto<AnswerDto> getAllAnswersByVotes(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "answerPageByVoteDtoImpl");
        return pageDto(param);
    }
}
