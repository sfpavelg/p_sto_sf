package com.javamentor.qa.platform.dao.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.tag.TrackedTagDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.TrackedTag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class TrackedTagDaoImpl extends ReadWriteDaoImpl<TrackedTag, Long> implements TrackedTagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public boolean existsByUserIdAndTagId(Long userId, Long tagId) {
        long count = (long) entityManager
                .createQuery("SELECT COUNT(tt) FROM TrackedTag tt WHERE tt.trackedTag.id =: tagId and tt.user.id =: userId")
                .setParameter("tagId", tagId).setParameter("userId",userId).getSingleResult();
        return count > 0;
    }
}
