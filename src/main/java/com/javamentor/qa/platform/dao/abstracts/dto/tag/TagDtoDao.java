package com.javamentor.qa.platform.dao.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TagDtoDao {
    List<TagDto> getTagDtoById(Long id);

    List<RelatedTagsDto> getRelatedTagsDto();

    Map<Long, List<TagDto>> getMapTagDtoAndQuestionId();

    Optional<TagDto> getById(Long id);

    List<TagDto> getIgnoredTagByUserId(Long id);

    Map<Long, List<TagDto>> getTagsMapByQuestionId(List<Long> qId);

    List<TagDto> getTrackedTagsByUserId(Long userId);

    List<TagDto> getTagsByName(String tagName);

}
