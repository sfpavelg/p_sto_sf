package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarksDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarksDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.BookmarksDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class BookmarksDtoServiceImpl implements BookmarksDtoService {

    private final BookmarksDtoDao bookmarksDtoDao;
    private final TagDtoDao tagDtoDao;

    @Override
    public List<BookmarksDto> getBookmarksDto(Long userId) {
        List<BookmarksDto> bookmarks = bookmarksDtoDao.getBookmarksDtoById(userId);
        Map<Long, List<TagDto>> tags = tagDtoDao.getMapTagDtoAndQuestionId();

        bookmarks.stream()
                .filter(b -> tags.containsKey(b.getQuestionId()))
                .forEach(b -> b.setTagDtoList(tags.get(b.getQuestionId())));
        return bookmarks;
    }
}
