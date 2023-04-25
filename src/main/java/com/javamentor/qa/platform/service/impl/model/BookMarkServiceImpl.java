package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookMarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.service.abstracts.model.BookMarkService;
import org.springframework.stereotype.Service;

@Service
public class BookMarkServiceImpl extends ReadWriteServiceImpl<BookMarks, Long> implements BookMarkService {
    private final BookMarkDao bookMarkDao;

    public BookMarkServiceImpl(BookMarkDao bookMarkDao) {
        super(bookMarkDao);
        this.bookMarkDao = bookMarkDao;
    }
}
