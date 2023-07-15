package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ChatDao;
import com.javamentor.qa.platform.models.entity.chat.Chat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class ChatDaoImpl extends ReadWriteDaoImpl<Chat, Long> implements ChatDao {
    @PersistenceContext
    private EntityManager entityManager;

    public void deleteById(Long chatId){

        String sql = "DELETE FROM message WHERE chat_id = :chatId";
        entityManager.createNativeQuery(sql).setParameter("chatId", chatId).executeUpdate();

        sql = "DELETE FROM groupchat_has_users WHERE chat_id = :chatId";
        entityManager.createNativeQuery(sql).setParameter("chatId", chatId).executeUpdate();

        String hql = "DELETE GroupChat WHERE id = :id";
        entityManager.createQuery(hql).setParameter("id", chatId).executeUpdate();

        hql = "DELETE SingleChat WHERE id = :id";
        entityManager.createQuery(hql).setParameter("id", chatId).executeUpdate();

        hql = "DELETE Chat WHERE id = :id";
        entityManager.createQuery(hql).setParameter("id", chatId).executeUpdate();
    }
}
