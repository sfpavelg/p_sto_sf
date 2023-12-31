package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.CommentDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import com.javamentor.qa.platform.service.abstracts.dto.question.CommentDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentDtoServiceImpl implements CommentDtoService {
    private final CommentDtoDao commentDtoDao;

    @Override
    public List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId) {
        return commentDtoDao.getAllCommentDtoByQuestionId(questionId);
    }

    @Override
    public Optional<QuestionCommentDto> getCommentById(Long id) {
        return commentDtoDao.getCommentById(id);
    }
}
