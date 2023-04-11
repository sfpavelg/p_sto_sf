package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;

import java.util.List;
import java.util.Optional;

public interface CommentDtoService {
   List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId);

   Optional<QuestionCommentDto> getCommentById(Long id);
}
