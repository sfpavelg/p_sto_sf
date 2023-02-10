package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.QuestionDao;
import com.javamentor.qa.platform.dao.abstracts.model.TagDao;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class QuestionServiceImpl extends ReadWriteServiceImpl<Question, Long> implements QuestionService {
    private final QuestionDao questionDao;

    private final TagDao tagDao;
    private final QuestionDtoService questionDtoService;

    public QuestionServiceImpl(QuestionDao questionDao, TagDao tagDao, QuestionDtoService questionDtoService) {
        super(questionDao);
        this.questionDao = questionDao;
        this.tagDao = tagDao;
        this.questionDtoService = questionDtoService;
    }

    @Override
    @Transactional
    public QuestionDto addQuestion(Question question, User user) throws NotFoundException {
        List<Tag> tagListOrigin = question.getTags();
        List<String> tagNamesList = new ArrayList<>();
        tagListOrigin.forEach(tag -> tagNamesList.add(tag.getName()));
        List<Tag> existsInDbTags = tagDao.getTagsByName(tagNamesList);
        List<Tag> doesntExistTags = tagListOrigin.stream()
                .filter(tag -> existsInDbTags.stream().allMatch(t -> t.getName().equals(tag.getName())))
                .collect(Collectors.toList());
        tagDao.persistAll(doesntExistTags);
        question.setTags(Stream.of(existsInDbTags, doesntExistTags).flatMap(Collection::stream).collect(Collectors.toList()));
        question.setUser(user);
        questionDao.persist(question);
        return questionDtoService.getQuestionDtoById(question.getId());
    }
}
