package com.javamentor.qa.platform.dao.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.MessageDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class MessageDtoDaoImpl implements MessageDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageDto> getAllUserMessages(Long userId) {
        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.chat.MessageDto(" +
                        "m.id," +
                        "m.message," +
                        "m.userSender.nickname," +
                        "m.userSender.id," +
                        "m.userSender.imageLink," +
                        "m.persistDate)" +
                        "FROM Message m " +
                        "WHERE m.userSender.id = :userId", MessageDto.class)
                .setParameter("userId", userId);
        return (List<MessageDto>) query.getResultList();
        // кастинг взял из другого метода работает и без него, убрать?
    }

    @Override
    public Optional<Object> getMessageDtoById(Long messageId) {
        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.chat.MessageDto(" +
                        "m.id," +
                        "m.message," +
                        "m.userSender.nickname," +
                        "m.userSender.id," +
                        "m.userSender.imageLink," +
                        "m.persistDate)" +
                        "FROM Message m " +
                        "WHERE m.id = :messageId", MessageDto.class)
                .setParameter("messageId", messageId);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

}
