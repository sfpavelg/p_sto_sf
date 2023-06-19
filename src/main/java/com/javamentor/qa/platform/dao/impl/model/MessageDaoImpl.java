package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.MessageDao;
import com.javamentor.qa.platform.models.entity.chat.Message;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class MessageDaoImpl extends ReadWriteDaoImpl<Message, Long> implements MessageDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Message getLastMessageByChatId(Long id) {
        Query query = entityManager.createQuery("SELECT l FROM Message l  " +
                        "JOIN FETCH l.chat JOIN FETCH l.userSender AS us JOIN FETCH us.role " +
                        "WHERE l.chat.id = :chatId ORDER BY l.persistDate DESC")
                .setParameter("chatId", id)
                .setMaxResults(1);
        List<Message> resultList = (List<Message>) query.getResultList();
        if (resultList.size() > 0) {
            return resultList.get(0);
        } return null;
    }
}
