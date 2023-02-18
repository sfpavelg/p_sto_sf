package com.javamentor.qa.platform.service.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import javassist.NotFoundException;

import java.util.List;

public interface TagDtoService {
    List<RelatedTagsDto> getRelatedTagsDto();
    TagDto getById(Long id) throws NotFoundException;
}
