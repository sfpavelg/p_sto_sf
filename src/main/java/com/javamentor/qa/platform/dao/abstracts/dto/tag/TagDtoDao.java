package com.javamentor.qa.platform.dao.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;

import java.util.List;

public interface TagDtoDao {
    List<TagDto> getTagDtoById(Long id);
    List<RelatedTagsDto> getRelatedTagsDto ();
}
