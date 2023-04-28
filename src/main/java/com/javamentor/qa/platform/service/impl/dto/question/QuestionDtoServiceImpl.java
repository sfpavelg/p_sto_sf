package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoSortedByNewestDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDaoSortedByPopularity;
import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoWithoutAnswerPaginationDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import static java.util.stream.Collectors.toList;


@Service
public class QuestionDtoServiceImpl extends PageDtoService<QuestionViewDto> implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;
    private final AnswerDtoDao answerDtoDao;
    private final QuestionDtoWithoutAnswerPaginationDao questionDtoWithoutAnswerPaginationDao;
    private final QuestionDtoDaoSortedByPopularity questionDtoDaoSortedByPopularity;
    private final QuestionDtoSortedByNewestDao questionDtoSortedByNewestDao;

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
        this.questionDtoWithoutAnswerPaginationDao = questionDtoWithoutAnswerPaginationDao;
        this.questionDtoDaoSortedByPopularity = questionDtoDaoSortedByPopularity;
        this.questionDtoSortedByNewestDao = questionDtoSortedByNewestDao;
    }


    @Override
    public QuestionViewDto getQuestionDtoById(Long id) throws NotFoundException {
        Optional<QuestionViewDto> questionDtoOptional = questionDtoDao.getQuestionDtoById(id);

        if (questionDtoOptional.isPresent()) {
            QuestionViewDto questionViewDto = questionDtoOptional.get();
            questionViewDto.setListTagDto(tagDtoDao.getTagDtoById(id));
            questionViewDto.setListAnswerDto(answerDtoDao.getAllByQuestionIdSortedByUsefulAndCount(id));

            return questionViewDto;
        }
        throw new NotFoundException("QuestionDto with id = " + id + " not found");
    }

    @Override
    public PageDto<QuestionViewDto> getAllQuestionDto(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "paginationQuestionDtoDaoGetAllImpl");

        PageDto<QuestionViewDto> pageDto = pageDto(param);
        List<QuestionViewDto> listQuestionViewDto = pageDto.getItems();
        List<Long> questionId = listQuestionViewDto.stream().map(QuestionViewDto::getId).collect(toList());
        Map<Long, List<TagDto>> tagsMap = tagDtoDao.getTagsMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListTagDto(tagsMap.get(lQuestionDto.getId())));
        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListAnswerDto(answersMap.get(lQuestionDto.getId())));


        return pageDto;
    }

    @Override
    public PageDto<QuestionViewDto> getPageWithListQuestionDtoWithoutAnswer(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoWithoutAnswerPaginationDaoImpl");
        PageDto<QuestionViewDto> pageDto = pageDto(param);
        List<QuestionViewDto> questionViewDtoList = pageDto.getItems();
        List<Long> listQuestionId = new ArrayList<>();
        for (QuestionViewDto questionViewDto : questionViewDtoList) {
            listQuestionId.add(questionViewDto.getId());
        }
        Map<Long, List<TagDto>> tagsMapByQuestionId = questionDtoWithoutAnswerPaginationDao.getTagsMapByQuestionId(listQuestionId);
        for (QuestionViewDto questionViewDto : questionViewDtoList) {
            questionViewDto.setListTagDto(tagsMapByQuestionId.get(questionViewDto.getId()));
        }
        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(listQuestionId);
        questionViewDtoList.forEach(lQuestionDto -> lQuestionDto.setListAnswerDto(answersMap.get(lQuestionDto.getId())));
        return pageDto;
    }

    @Override
    public PageDto<QuestionViewDto> getPageWithListMostPopularQuestionDto(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoDaoSortedByPopularityImpl");

        PageDto<QuestionViewDto> pageDto = pageDto(param);
        List<QuestionViewDto> listQuestionViewDto = pageDto.getItems();
        List<Long> questionId = listQuestionViewDto.stream().map(QuestionViewDto::getId).collect(toList());
        Map<Long, List<TagDto>> tagsMap = tagDtoDao.getTagsMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListTagDto(tagsMap.get(lQuestionDto.getId())));
        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListAnswerDto(answersMap.get(lQuestionDto.getId())));

        return pageDto;
    }

    @Override
    public PageDto<QuestionViewDto> getPageWithListQuestionDtoSortedByNewest(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoSortedByNewestDaoImpl");
        PageDto<QuestionViewDto> pageDto = pageDto(param);
        List<QuestionViewDto> listQuestionViewDto = pageDto.getItems();
        List<Long> questionId = listQuestionViewDto.stream().map(QuestionViewDto::getId).collect(toList());
        Map<Long, List<TagDto>> tagsMap = tagDtoDao.getTagsMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListTagDto(tagsMap.get(lQuestionDto.getId())));
        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(questionId);
        listQuestionViewDto.forEach(lQuestionDto -> lQuestionDto.setListAnswerDto(answersMap.get(lQuestionDto.getId())));

        return pageDto;
    }
}
