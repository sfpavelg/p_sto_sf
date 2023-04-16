package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookmarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.service.abstracts.model.BookmarkService;
import org.springframework.stereotype.Repository;

@Repository
public class BookmarkServiceImpl extends ReadWriteServiceImpl<BookMarks, Long> implements BookmarkService {
    public BookmarkServiceImpl(BookmarkDao bookmarkDao) {
        super(bookmarkDao);
    }
}
