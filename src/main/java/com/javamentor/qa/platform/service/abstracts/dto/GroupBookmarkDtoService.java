package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.BookmarkDto;
import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;

import java.util.List;
import java.util.Optional;

public interface GroupBookmarkDtoService {
    List<GroupBookmarkDto> getGroupBookmark(Long userId);

}
