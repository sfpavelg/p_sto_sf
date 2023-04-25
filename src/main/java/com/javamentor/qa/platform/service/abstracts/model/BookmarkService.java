package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

public interface BookmarkService extends ReadWriteService<BookMarks, Long>{
    BookMarks persistByQuestionId(Long id, User user) throws NotFoundException;
}
