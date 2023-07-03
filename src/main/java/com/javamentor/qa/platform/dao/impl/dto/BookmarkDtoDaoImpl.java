package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.BookmarkDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.BookmarkDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

@Repository
public class BookmarkDtoDaoImpl implements BookmarkDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<BookmarkDto> getBookmarkById(Long id) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.BookmarkDto(" +
                "b.id, " +
                "b.question.id, " +
                "b.user.id, " +
                "b.note) " +
                "FROM BookMarks b " +
                "WHERE b.id = :id", BookmarkDto.class).setParameter("id", id);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

}
