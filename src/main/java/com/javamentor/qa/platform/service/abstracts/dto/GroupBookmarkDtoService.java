package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;

import java.util.Optional;

public interface GroupBookmarkDtoService {
    Optional<GroupBookmarkDto> getGroupBookmarkById(Long id);
}
