package com.javamentor.qa.platform.service.abstracts.dto.tag;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;

import java.util.HashMap;

public interface TagViewDtoService {

    PageDto<TagViewDto> getSortedByDateTagList(HashMap<String, Object> params);

    PageDto<TagViewDto> getPageWithListTagDtoSortedByName(HashMap<String, Object> param);

    PageDto<TagViewDto> getSortedByPopularity(HashMap<String, Object> params);

    PageDto<TagViewDto> getPageWithListTagDtoSortedBySyllable(HashMap<String, Object> param);

}
