package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

import java.util.Optional;

public interface VoteQuestionService extends ReadWriteService<VoteQuestion, Long> {
    Optional<VoteQuestion> getByUserId(Long questionId, Long userId);

    Long voteForQuestion(Long questionId, User user, VoteType voteType) throws NotFoundException;
}
