package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.BookmarksDto;

import javax.persistence.Tuple;
import java.util.List;

public interface BookmarksDtoDao {
    List<Tuple> getTupleTagsForBookmarksDtoById(Long userId);

    List<BookmarksDto> getBookmarksDtoById(Long userId);
}

