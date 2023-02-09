package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.abstracts.model.QuestionDao;
import com.javamentor.qa.platform.dao.abstracts.model.TagDao;
import com.javamentor.qa.platform.dao.abstracts.model.UserDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionDtoService;
import com.javamentor.qa.platform.webapp.converters.QuestionConverter;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
public class QuestionDtoServiceImpl implements QuestionDtoService {

    private final QuestionDtoDao questionDtoDao;
    private final TagDtoDao tagDtoDao;
    private final QuestionDao questionDao;

    private final UserDao userDao;

    private final TagDao tagDao;

    private final QuestionConverter questionConverter;

    public QuestionDtoServiceImpl(QuestionDtoDao questionDtoDao, TagDtoDao tagDtoDao, QuestionDao questionDao, UserDao userDao, TagDao tagDao, QuestionConverter questionConverter) {
        this.questionDtoDao = questionDtoDao;
        this.tagDtoDao = tagDtoDao;
        this.questionDao = questionDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.questionConverter = questionConverter;
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
    @Transactional
    public QuestionDto addQuestion(QuestionCreateDto questionCreateDto, Principal principal) throws NotFoundException {
        Question question = questionConverter.questionCreateDtoToQuestion(questionCreateDto);
        List<Tag> tagListDto = question.getTags();
        List<String> tagNamesList = new ArrayList<>();
        tagListDto.forEach(tag -> tagNamesList.add(tag.getName()));
        List<Tag> existsInDbTags = tagDao.getTagsByName(tagNamesList);
        List<Tag> doesntExistTags = tagListDto.stream().filter(tag -> {
            for (Tag t : existsInDbTags) {
                if (t.getName().equals(tag.getName())) {
                    return false;
                }
            }
            return true;
        }).collect(Collectors.toList());
        tagDao.persistAll(doesntExistTags);
        question.setTags(Stream.of(existsInDbTags, doesntExistTags).flatMap(Collection::stream).collect(Collectors.toList()));
        Optional<User> user = userDao.getByEmail(principal.getName());
        user.ifPresent(question::setUser);
        questionDao.persist(question);
        return getQuestionDtoById(question.getId());
    }
}
