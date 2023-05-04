package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.GroupBookmarkDtoDao;
import com.javamentor.qa.platform.models.dto.GroupBookmarkDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GroupBookmarkDtoDaoImpl implements GroupBookmarkDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GroupBookmarkDto> getGroupBookmark(Long userId) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.GroupBookmarkDto (" +
                        "gb.id, " +
                        "gb.title) " +
                        "from GroupBookmark gb " +
                        "where gb.user.id = :userId "
                , GroupBookmarkDto.class)
                .setParameter("userId", userId);

        return (List<GroupBookmarkDto>) query.getResultList();
    }

}
