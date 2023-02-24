package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Question;


public interface QuestionService extends ReadWriteService<Question, Long> {
    Long voteUpQuestion(Long userId, Long questionId);
    Long getSumVoteUp(Long questionId);
    Long voteDownQuestion(Long userId, Long questionId);
}
