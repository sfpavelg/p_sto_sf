package com.javamentor.qa.platform.dao.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.GroupChatDtoDao;
import com.javamentor.qa.platform.models.dto.chat.GroupChatDto;
import com.javamentor.qa.platform.models.entity.chat.ChatType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Repository
public class GroupChatDtoDaoImpl implements GroupChatDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<GroupChatDto> getGroupChatDto(Long id, ChatType group) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.chat.GroupChatDto(" +
                "c.id," +
                "c.title," +
                "c.persistDate," +
                "m.message," +
                "gc.imageLink)" +
                " from Chat c join Message m " +
                "on c.id = m.chat.id " +
                "join GroupChat gc " +
                "on c.id=gc.chat.id " +
                "where c.chatType=:chatType " +
                "and m.persistDate=(select max(v.persistDate) from Message v where v.chat.id = c.id )" +
                "and m.userSender.id = :id", GroupChatDto.class).setParameter("id", id).setParameter("chatType", group);
        return query.getResultList();

    }
}
