package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoWithoutAnswerPaginationDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

@Service
public class QuestionDtoServiceImpl extends PageDtoService<QuestionDto> implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;
    private final QuestionDtoWithoutAnswerPaginationDao questionDtoWithoutAnswerPaginationDao;

    public QuestionDtoServiceImpl(
            QuestionDtoDao questionDtoDao,
            TagDtoDao tagDtoDao,
            Map<String, PageDtoDao<QuestionDto>> beansMap, QuestionDtoWithoutAnswerPaginationDao questionDtoWithoutAnswerPaginationDao) {
        super(beansMap);
        this.questionDtoDao = questionDtoDao;
        this.tagDtoDao = tagDtoDao;
        this.questionDtoWithoutAnswerPaginationDao = questionDtoWithoutAnswerPaginationDao;
    }


    @Override
    public QuestionDto getQuestionDtoById(Long id) throws NotFoundException {
        Optional<QuestionDto> questionDtoOptional = questionDtoDao.getQuestionDtoById(id);

        if (questionDtoOptional.isPresent()) {
            QuestionDto questionDto = questionDtoOptional.get();
            questionDto.setListTagDto(tagDtoDao.getTagDtoById(id));
            return questionDto;
        }
        throw new NotFoundException("QuestionDto with id = " + id + " not found");
    }

    @Override
    public PageDto<QuestionDto> getPageWithListQuestionDtoWithoutAnswer(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoWithoutAnswerPaginationDaoImpl");
        PageDto<QuestionDto> pageDto = pageDto(param);
        List<QuestionDto> questionDtoList =  pageDto.getItems();
        List<Long> listQuestionId = new ArrayList<>();
        for (QuestionDto questionDto: questionDtoList){
            listQuestionId.add(questionDto.getId());
        }
        Map<Long, List<TagDto>> tagsMapByQuestionId = questionDtoWithoutAnswerPaginationDao.getTagsMapByQuestionId(listQuestionId);
        for (QuestionDto questionDto: questionDtoList) {
            questionDto.setListTagDto(tagsMapByQuestionId.get(questionDto.getId()));
        }
        return pageDto;
    }
}
