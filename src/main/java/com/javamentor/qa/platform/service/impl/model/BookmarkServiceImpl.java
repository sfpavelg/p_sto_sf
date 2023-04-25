package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookmarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.BookmarkService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookmarkServiceImpl extends ReadWriteServiceImpl<BookMarks, Long> implements BookmarkService {
    private final QuestionService questionService;
    private final BookmarkDao bookmarkDao;

    public BookmarkServiceImpl(BookmarkDao bookmarkDao, QuestionService questionService, BookmarkDao bookmarkDao1) {
        super(bookmarkDao);
        this.questionService = questionService;
        this.bookmarkDao = bookmarkDao1;
    }

    @Override
    @Transactional
    public BookMarks persistByQuestionId(Long id, User user) throws NotFoundException {
        BookMarks bookMarks = new BookMarks();
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        Optional<Question> question = questionService.getById(id);
        if (question.isEmpty()) {
            throw new NotFoundException("Question not found");
        }
        if (bookmarkDao.duplicateQuestionCheck(user.getId(), id)) {
            throw new IllegalArgumentException("Question already exist");
        }
        bookMarks.setUser(user);
        bookMarks.setQuestion(question.get());
        persist(bookMarks);
        return bookMarks;
    }
}
