package com.javamentor.qa.platform.service.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;

import java.util.List;

public interface TagDtoService {
    List<RelatedTagsDto> getRelatedTagsDto();

    List<TagDto> getIgnoredTagByUserId(Long id);
}
