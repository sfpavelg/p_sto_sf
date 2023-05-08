package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupBookmarkDao;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class GroupBookmarkDaoImpl extends ReadWriteDaoImpl<GroupBookmark, Long> implements GroupBookmarkDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean duplicateGroupBookmarkName(Long userId, String groupName) {
        long count = (long) entityManager
                .createQuery("SELECT COUNT(it) from GroupBookmark it where it.title = :groupName" +
                        " and it.user.id = :userId")
                .setParameter("groupName", groupName).setParameter("userId", userId).getSingleResult();
        return count > 0;
    }
}