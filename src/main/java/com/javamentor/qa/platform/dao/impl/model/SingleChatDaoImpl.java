package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.SingleChatDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class SingleChatDaoImpl extends ReadWriteDaoImpl<SingleChat, Long> implements SingleChatDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Optional<SingleChat> getSingleChatWithUsersById(Long chatId){
        return SingleResultUtil.getSingleResultOrNull(entityManager.createQuery(
                        "select u from SingleChat u join fetch u.userOne join fetch u.useTwo where u.id= :id")
                .setParameter("id", chatId));
    }
}
