package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BlockChatUserListDao;
import com.javamentor.qa.platform.models.entity.user.BlockChatUserList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class BlockChatUserListDaoImpl extends ReadWriteDaoImpl<BlockChatUserList, Long> implements BlockChatUserListDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isUserBlocked(Long userId, Long blockedUserId) {
        return (boolean) entityManager.createQuery(
                        "SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END FROM BlockChatUserList b WHERE b.profile.id = :profile AND b.blocked.id = :blocked")
                .setParameter("profile", userId)
                .setParameter("blocked", blockedUserId).getSingleResult();
    }
}
