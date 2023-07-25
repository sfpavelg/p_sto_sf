package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.BookMarks;

public interface BookmarkDao extends ReadWriteDao<BookMarks, Long>{
    boolean duplicateQuestionCheck(Long userId, Long questionId);
}
