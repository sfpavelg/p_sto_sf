package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;

import java.util.Optional;

public interface GroupBookmarkDtoDao {
    Optional<GroupBookmarkDto> getGroupBookmarkById(Long id);
}
