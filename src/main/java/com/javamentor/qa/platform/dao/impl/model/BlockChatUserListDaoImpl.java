package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BlockChatUserListDao;
import com.javamentor.qa.platform.models.entity.user.BlockChatUserList;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigInteger;

@Repository
public class BlockChatUserListDaoImpl extends ReadWriteDaoImpl<BlockChatUserList, Long> implements BlockChatUserListDao {

    @PersistenceContext
    private EntityManager entityManager;

    public boolean isUserBlocked(Long userId, Long blockedUserId) {
        return ((BigInteger) entityManager.createNativeQuery(
                "SELECT COUNT(*) FROM block_chat_user_list WHERE profile_id = :profile AND blocked_id = :blocked")
                .setParameter("profile", userId)
                .setParameter("blocked", blockedUserId)
             .getSingleResult()).intValue() >= 1;
    }
}
