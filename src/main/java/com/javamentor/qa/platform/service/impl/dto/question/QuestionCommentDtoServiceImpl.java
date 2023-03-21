package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionCommentDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.question.QuestionCommentDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionCommentDtoServiceImpl implements QuestionCommentDtoService {
    private final QuestionCommentDtoDao questionCommentDtoDao;

    @Override
    public List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId) {
        return questionCommentDtoDao.getAllCommentDtoByQuestionId(questionId);
    }
}
