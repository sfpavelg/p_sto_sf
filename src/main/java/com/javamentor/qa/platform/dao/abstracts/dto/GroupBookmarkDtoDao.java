package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;

import java.util.Optional;
import java.util.List;

public interface GroupBookmarkDtoDao {
    Optional<GroupBookmarkDto> getGroupBookmarkById(Long id);

    List<GroupBookmarkDto> getGroupBookmark(Long userId);
}
