package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoSortedByNewestDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoDaoSortedByPopularity;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoWithoutAnswerPaginationDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;


@Service
public class QuestionDtoServiceImpl extends PageDtoService<QuestionViewDto> implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;
    private final AnswerDtoDao answerDtoDao;

    public QuestionDtoServiceImpl(
            QuestionDtoDao questionDtoDao,
            TagDtoDao tagDtoDao,
            AnswerDtoDao answerDtoDao,
            Map<String, PageDtoDao<QuestionViewDto>> beansMap,
            QuestionDtoWithoutAnswerPaginationDao questionDtoWithoutAnswerPaginationDao,
            QuestionDtoDaoSortedByPopularity questionDtoDaoSortedByPopularity,
            QuestionDtoSortedByNewestDao questionDtoSortedByNewestDao) {
        super(beansMap);
        this.questionDtoDao = questionDtoDao;
        this.tagDtoDao = tagDtoDao;
        this.answerDtoDao = answerDtoDao;
    }


    @Override
    public QuestionDto getQuestionDtoById(Long id) throws NotFoundException {
        Optional<QuestionDto> questionDtoOptional = questionDtoDao.getQuestionDtoById(id);

        if (questionDtoOptional.isPresent()) {
            QuestionDto questionDto = questionDtoOptional.get();
            questionDto.setListTagDto(tagDtoDao.getTagDtoById(id));
            questionDto.setListAnswerDto(answerDtoDao.getAllByQuestionIdSortedByUsefulAndCount(id));

            return questionDto;
        }
        throw new NotFoundException("QuestionDto with id = " + id + " not found");
    }

}
