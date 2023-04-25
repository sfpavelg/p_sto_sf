package com.javamentor.qa.platform.dao.abstracts.dto.tag.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;

/**
 * Interface for returning PageDto<TagViewDto> sorted by popularity
 */
public interface TagPageByPopularity extends PageDtoDao<TagViewDto> {
}
