package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarksDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarksDto;
import com.javamentor.qa.platform.service.abstracts.dto.BookmarksDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookmarksDtoServiceImpl implements BookmarksDtoService {

    private final BookmarksDtoDao bookmarksDtoDao;
    private final TagDtoDao tagDtoDao;

    @Override
    public List<BookmarksDto> getBookmarksDto(Long userId) {
        List<BookmarksDto> bookmarksDtoList = bookmarksDtoDao.getBookmarksDtoById(userId);
        bookmarksDtoList.forEach(b -> b.setTagDtoList(tagDtoDao.getTagDtoById(b.getQuestionId())));
        return bookmarksDtoList;
    }
}
