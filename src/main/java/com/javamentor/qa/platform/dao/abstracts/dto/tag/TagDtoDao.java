package com.javamentor.qa.platform.dao.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;

import java.util.List;
import java.util.Optional;

public interface TagDtoDao {
    List<TagDto> getTagDtoById(Long id);

    List<RelatedTagsDto> getRelatedTagsDto();

    Optional<TagDto> getById(Long id);

    List<TagDto> getIgnoredTagByUserId(Long id);

    List<TagViewDto> getSortedByDateTagList(int items, int page);
}
