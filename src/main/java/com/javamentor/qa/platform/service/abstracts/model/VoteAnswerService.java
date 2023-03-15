package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.answer.VoteAnswer;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;


public interface VoteAnswerService extends ReadWriteService<VoteAnswer, Long> {

    Long voteForAnswer(Long answerId, User user, VoteType voteType) throws NotFoundException;
}
