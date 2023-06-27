package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarksDtoDao;
import com.javamentor.qa.platform.models.dto.BookmarksDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BookmarksDtoDaoImpl implements BookmarksDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<BookmarksDto> getBookmarksDtoById(Long userId) {
        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.BookmarksDto(" +
                        "q.id, " +
                        "q.title, " +
                        "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
                        "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'UP_VOTE') - " +
                        "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'DOWN_VOTE'), " +
                        "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id), " +
                        "q.persistDateTime)" +
                "from Question q, BookMarks b where q.id = b.question.id and b.user.id = :userId", BookmarksDto.class)
                .setParameter("userId", userId)
                .getResultList();
    }
}
