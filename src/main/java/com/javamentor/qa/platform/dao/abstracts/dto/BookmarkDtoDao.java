package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.BookmarkDto;

import java.util.Optional;

public interface BookmarkDtoDao {
    Optional<BookmarkDto> getBookmarkById(Long id);
}
