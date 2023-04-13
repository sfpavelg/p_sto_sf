package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookMarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import org.springframework.stereotype.Repository;

@Repository
public class BookMarkDaoImpl extends ReadWriteDaoImpl<BookMarks, Long> implements BookMarkDao {
}
