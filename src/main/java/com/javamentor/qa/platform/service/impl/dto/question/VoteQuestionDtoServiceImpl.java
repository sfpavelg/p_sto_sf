package com.javamentor.qa.platform.service.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.VoteQuestionDtoDao;
import com.javamentor.qa.platform.models.dto.question.VoteQuestionDto;
import com.javamentor.qa.platform.models.entity.question.VoteQuestion;
import com.javamentor.qa.platform.service.abstracts.dto.answer.VoteQuestionDtoService;

import java.util.Optional;

public class VoteQuestionDtoServiceImpl implements VoteQuestionDtoService {
    private final VoteQuestionDtoDao voteQuestionDtoDao;

    public VoteQuestionDtoServiceImpl(VoteQuestionDtoDao voteQuestionDtoDao) {
        this.voteQuestionDtoDao = voteQuestionDtoDao;
    }

    @Override
    public Optional<VoteQuestionDto> getVoteByQuestionIdAndUserId(Long questionId, Long userId) {
        return voteQuestionDtoDao.getVoteByQuestionIdAndUserId(questionId, userId);
    }

}
