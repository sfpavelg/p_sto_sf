package com.javamentor.qa.platform.dao.abstracts.dto.question.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;

import java.util.List;
import java.util.Map;

public interface QuestionDtoWithoutAnswerPaginationDao  extends PageDtoDao<QuestionViewDto> {

    Map<Long, List<TagDto>> getTagsMapByQuestionId(List<Long> qId);

}
