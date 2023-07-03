package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoByTagPaginationDao;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public PageDto<QuestionDto> getPageWithListQuestionDtoByTag(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "questionDtoByTagPaginationDaoImpl");
        PageDto<QuestionDto> result = pageDto(param);
        List<QuestionDto> questionDtoList = result.getItems();
        List<Long> questionId = questionDtoList.stream().map(QuestionDto::getId).collect(toList());
        Map<Long, List<TagDto>> tagsMap = tagDtoDao.getTagsMapByQuestionId(questionId);
        questionDtoList.forEach(questionDto -> questionDto.setListTagDto(tagsMap.get(questionDto.getId())));
        Map<Long, List<AnswerDto>> answersMap = answerDtoDao.getAnswersMapByQuestionId(questionId);
        questionDtoList.forEach(questionDto -> questionDto.setListAnswerDto(answersMap.get(questionDto.getId())));
        return result;
    }

    @Override
    public Long getCountQuestionDto() {
        return questionDtoDao.getCountQuestionDto();
    }
    @Override
    public List<QuestionDto> getQuestionDtoByUserIdAndValue(Long userId, String value) throws NotFoundException {
        List<QuestionDto> questionDtoList = questionDtoDao.getQuestionDtoByParameters(userId, getTextByValue(value),
                getTagsByValue(value),
                getNumericParameterByValueAndRegex(value,"views:\\d+"),
                getNumericParameterByValueAndRegex(value, "user:\\d+"),
                getNumericParameterByValueAndRegex(value,"answers:\\d+" ));
        if (!questionDtoList.isEmpty()) {
            for (QuestionDto questionDto : questionDtoList) {
                questionDto.setListAnswerDto(answerDtoDao.getAllByQuestionIdSortedByUsefulAndCount(questionDto.getId()));
                questionDto.setListTagDto(tagDtoDao.getTagDtoById(questionDto.getId()));
            }
        }
        return questionDtoList;
    }
    private List<String> getTextByValue(String text) {
        return List.of(text.replaceAll("\\[.+?]", "")
                .replaceAll("user:\\d+", "")
                .replaceAll("answers:\\d+", "")
                .replaceAll("views:\\d+", "")
                .trim()
                .replaceAll("\\s+", " ").split(" ", 0));
    }

    private Long getNumericParameterByValueAndRegex(String text, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }

    private List<String> getTagsByValue(String value) {
        int end = 0;
        List<String> tags = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\[.+?]").matcher(value);
        while (matcher.find(end)) {
            tags.add(value.substring(matcher.start(), matcher.end()).replaceAll("\\[|\\]", ""));
            end = matcher.end();
        }
        return tags;
    }
}
