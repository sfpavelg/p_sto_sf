package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;


public interface VoteAnswerService extends ReadWriteService<VoteAnswer, Long> {

    Long voteUpForAnswer(Long answerId, User user) throws NotFoundException;

    Long voteDownForAnswer(Long answerId, User user) throws NotFoundException;
}
