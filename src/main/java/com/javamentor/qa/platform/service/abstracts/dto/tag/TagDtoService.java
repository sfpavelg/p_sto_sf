package com.javamentor.qa.platform.service.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface TagDtoService {
    List<RelatedTagsDto> getRelatedTagsDto();

    Optional<TagDto> getById(Long id);

    List<TagDto> getIgnoredTagByUserId(Long id);

    List<TagDto> getTrackedTagsByUserId(Long userId);

    PageDto<TagDto> getSortedByPopularity(HashMap<String, Object> params);
}
