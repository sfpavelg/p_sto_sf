package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarksDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarksDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.BookmarksDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import java.util.List;

@Service
@AllArgsConstructor
public class BookmarksDtoServiceImpl implements BookmarksDtoService {

    private final BookmarksDtoDao bookmarksDtoDao;

    @Override
    public List<BookmarksDto> getBookmarksDto(Long userId) {
        List<BookmarksDto> bookmarks = bookmarksDtoDao.getBookmarksDtoById(userId);
        List<Tuple> tags = bookmarksDtoDao.getTupleTagsForBookmarksDtoById(userId);

        bookmarks.forEach(bookmark -> tags.stream()
                .filter(tag -> bookmark.getQuestionId().equals(tag.get(3, Long.class)))
                .map(tag -> new TagDto(tag.get(0, Long.class), tag.get(1, String.class), tag.get(2, String.class)))
                .forEach(bookmark.getTagDtoList()::add));
        return bookmarks;
    }
}
