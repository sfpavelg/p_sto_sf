package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookmarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import com.javamentor.qa.platform.models.entity.question.Question;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.BookmarkService;
import com.javamentor.qa.platform.service.abstracts.model.QuestionService;
import com.javamentor.qa.platform.service.abstracts.model.UserService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookmarkServiceImpl extends ReadWriteServiceImpl<BookMarks, Long> implements BookmarkService {
    private final UserService userService;
    private final QuestionService questionService;
    private final BookmarkDao bookmarkDao;

    public BookmarkServiceImpl(BookmarkDao bookmarkDao, UserService userService, QuestionService questionService, BookmarkDao bookmarkDao1) {
        super(bookmarkDao);
        this.userService = userService;
        this.questionService = questionService;
        this.bookmarkDao = bookmarkDao1;
    }

    @Override
    @Transactional
    public void persistByQuestionId(Long id, User user, BookMarks bookMarks) throws NotFoundException {
        Optional<User> authorizedUser = userService.getById(user.getId());
        if (authorizedUser.isEmpty()) {
            throw new NotFoundException("User not found");
        }
        Optional<Question> question = questionService.getById(id);
        if (question.isEmpty()) {
            throw new NotFoundException("Question not found");
        }
        if (bookmarkDao.duplicateQuestionCheck(user.getId(), id)) {
            throw new IllegalArgumentException("Question already exist");
        }
        bookMarks.setUser(authorizedUser.get());
        bookMarks.setQuestion(question.get());
        persist(bookMarks);
    }
}
