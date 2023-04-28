package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarkDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarkDto;
import com.javamentor.qa.platform.service.abstracts.dto.BookmarkDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookmarkDtoServiceImpl implements BookmarkDtoService {
    private final BookmarkDtoDao bookmarkDtoDao;

    @Override
    public Optional<BookmarkDto> getBookmarkById(Long id) {
        return bookmarkDtoDao.getBookmarkById(id);
    }

}
