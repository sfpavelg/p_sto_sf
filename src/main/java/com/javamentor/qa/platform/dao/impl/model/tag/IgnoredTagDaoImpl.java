package com.javamentor.qa.platform.dao.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.tag.IgnoredTagDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.IgnoredTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class IgnoredTagDaoImpl extends ReadWriteDaoImpl<IgnoredTag, Long> implements IgnoredTagDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByUserIdAndTagId(Long userId, Long tagId) {
        long count = (long) entityManager
                .createQuery("SELECT COUNT(it) FROM IgnoredTag it WHERE it.ignoredTag.id =: tagId and it.user.id =: userId")
                .setParameter("tagId", tagId).setParameter("userId",userId).getSingleResult();
        return count > 0;
    }
}

