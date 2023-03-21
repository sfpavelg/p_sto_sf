package com.javamentor.qa.platform.service.abstracts.dto.question;

import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;

import java.util.List;

public interface QuestionCommentDtoService {
   List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId);
}
