package com.javamentor.qa.platform.service.abstracts.dto.answer;

import com.javamentor.qa.platform.models.dto.question.VoteQuestionDto;


import java.util.Optional;

public interface VoteQuestionDtoService {

    Optional<VoteQuestionDto> getVoteByQuestionIdAndUserId(Long questionId, Long userId);
}
