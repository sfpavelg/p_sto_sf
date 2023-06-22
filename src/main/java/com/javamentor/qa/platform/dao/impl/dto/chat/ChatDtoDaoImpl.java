package com.javamentor.qa.platform.dao.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.ChatDtoDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
@Repository
public class ChatDtoDaoImpl extends ReadWriteDaoImpl<SingleChat, Long> implements ChatDtoDao {
    @PersistenceContext
    private EntityManager entityManager;


    public List<ChatDto> getChatDtoFromSingleChatByUserIdAndValue(Long id, String value) {
        Query query = entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.chat.ChatDto(" +
                                "sc.id, " +
                                "case when scu1.id = :userId then scu2.nickname else scu1.nickname end," +
                                "case when scu1.id = :userId then scu2.imageLink else scu1.imageLink end," +
                                "(select m.message from Message as m where m.id = (select max(l.id) as mid from Message as l join l.chat join l.userSender as us join us.role where l.chat.id = sc.id))," +
                                "(select m.persistDate from Message as m where m.id = (select max(l.id) as mid from Message as l join l.chat join l.userSender as us join us.role where l.chat.id = sc.id)))" +

                                "FROM SingleChat sc JOIN sc.userOne AS scu1 " +
                                "JOIN sc.useTwo AS scu2 JOIN sc.chat JOIN scu2.role JOIN scu1.role " +
                                "WHERE scu1.id = :userId AND scu2.nickname LIKE :searchValue " +
                                "OR scu2.id = :userId AND scu1.nickname LIKE :searchValue", ChatDto.class)
                .setParameter("userId", id)
                .setParameter("searchValue", "%" + value + "%");
        return query.getResultList();
    }
    @Override
    public List<ChatDto> getChatDtoFromGroupChatByUserIdAndValue(Long id, String value) {
        Query query = entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.chat.ChatDto(" +
                                "gc.id, " +
                                "gc.chat.title," +
                                "'null'," +
                                "(select m.message from Message as m where m.id = (select max(l.id) as mid from Message as l join l.chat join l.userSender as us join us.role where l.chat.id = gc.id))," +
                                "(select m.persistDate from Message as m where m.id = (select max(l.id) as mid from Message as l join l.chat join l.userSender as us join us.role where l.chat.id = gc.id)))" +

                                "FROM GroupChat gc JOIN  gc.users " +
                                "AS gcc JOIN  gc.chat AS gcChat JOIN  gcc.role WHERE gcc.id = :userId" +
                                " AND gc.chat.title LIKE :searchValue", ChatDto.class)
                .setParameter("userId", id)
                .setParameter("searchValue", "%" + value + "%");
        return query.getResultList();
    }
}
