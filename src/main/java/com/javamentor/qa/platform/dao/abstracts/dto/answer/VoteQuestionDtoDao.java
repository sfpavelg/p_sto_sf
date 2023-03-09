package com.javamentor.qa.platform.dao.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.question.VoteQuestionDto;

import java.util.Optional;

public interface VoteQuestionDtoDao {

    Optional<VoteQuestionDto> getVoteByQuestionIdAndUserId(Long questionId, Long userId);

}
