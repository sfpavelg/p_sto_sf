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

    @Override
    public void persist(BlockChatUserList blockChatUserList) {
        BlockChatUserList existingBlock = entityManager.createQuery(
                        "SELECT b FROM BlockChatUserList b WHERE b.profile = :profile AND b.blocked = :blocked",
                        BlockChatUserList.class)
                .setParameter("profile", blockChatUserList.getProfile())
                .setParameter("blocked", blockChatUserList.getBlocked())
                .getResultList().stream().findFirst().orElse(null);
        if (existingBlock == null) {
            entityManager.persist(blockChatUserList);
        }
    }
}
