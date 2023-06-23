package com.javamentor.qa.platform.dao.abstracts.dto.answer.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;

/**
 * Interface for returning PageDto<AnswerDto> sorted by votes
 */
public interface AnswerPageByVoteDto extends PageDtoDao<AnswerDto> {
}
