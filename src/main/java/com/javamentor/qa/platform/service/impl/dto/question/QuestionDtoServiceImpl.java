package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoByTagPaginationDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoDao;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;


@Service
public class QuestionDtoServiceImpl extends PageDtoService<QuestionDto> implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;
    private final AnswerDtoDao answerDtoDao;
    private final QuestionDtoByTagPaginationDao questionDtoByTagPaginationDao;

    public QuestionDtoServiceImpl(
            QuestionDtoDao questionDtoDao,
            TagDtoDao tagDtoDao,
            AnswerDtoDao answerDtoDao,
            QuestionDtoByTagPaginationDao questionDtoByTagPaginationDao,
            Map<String, PageDtoDao<QuestionDto>> beansMap) {
        super(beansMap);
        this.questionDtoDao = questionDtoDao;
        this.tagDtoDao = tagDtoDao;
        this.answerDtoDao = answerDtoDao;
        this.questionDtoByTagPaginationDao = questionDtoByTagPaginationDao;
    }


    @Override
    public QuestionDto getQuestionDtoById(Long id, Long userId) throws NotFoundException {
        Optional<QuestionDto> questionDtoOptional = questionDtoDao.getQuestionDtoById(id, userId);

        if (questionDtoOptional.isPresent()) {
            QuestionDto questionDto = questionDtoOptional.get();
            questionDto.setListTagDto(tagDtoDao.getTagDtoById(id));
            questionDto.setListAnswerDto(answerDtoDao.getAllByQuestionIdSortedByUsefulAndCount(id));

            return questionDto;
        }
        throw new NotFoundException("QuestionDto with id = " + id + " not found");
    }

    @Override
    public  PageDto<QuestionDto> getPageWithListQuestionDtoByTag(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoByTagPaginationDaoImpl");
//        PageDto<QuestionDto> pageDto = pageDto(param);
//        List<QuestionDto> listQuestionDto = pageDto.getItems();

        //        List<Long> questionId = listQuestionViewDto.stream().map(QuestionViewDto::getId).collect(toList());
//        Map<Long, List<TagDto>> tagsMap = tagDtoDao.getTagsMapByQuestionId(questionId);
//        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListTagDto(tagsMap.get(lQuestionDto.getId())));
//        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(questionId);
//        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListAnswerDto(answersMap.get(lQuestionDto.getId())));
        return pageDto(param);
    }
}
