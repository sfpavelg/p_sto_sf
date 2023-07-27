package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BookmarkDao;
import com.javamentor.qa.platform.models.entity.BookMarks;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BookmarkDaoImpl extends ReadWriteDaoImpl<BookMarks, Long> implements BookmarkDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean duplicateQuestionCheck(Long userId, Long questionId) {
        long count = (long) entityManager
                .createQuery("SELECT COUNT(it) from BookMarks it where it.question.id = :questionId" +
                        " and it.user.id = :userId")
                .setParameter("questionId", questionId)
                .setParameter("userId", userId)
                .getSingleResult();
        return count > 0;
    }
}
