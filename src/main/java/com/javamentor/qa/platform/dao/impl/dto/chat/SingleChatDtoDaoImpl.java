package com.javamentor.qa.platform.dao.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.SingleChatDtoDao;
import com.javamentor.qa.platform.models.dto.chat.SingleChatDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class SingleChatDtoDaoImpl implements SingleChatDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<SingleChatDto> getSingleChatDto(User user) {
        TypedQuery<SingleChatDto> query = entityManager.createQuery(
                "SELECT NEW com.javamentor.qa.platform.models.dto.chat.SingleChatDto (" +
                        "sc.id, " +
                        "CASE WHEN sc.userOne.id = :id THEN sc.useTwo.fullName ELSE sc.userOne.fullName END, " +
                        "CASE WHEN sc.userOne.id = :id THEN sc.useTwo.imageLink ELSE sc.userOne.imageLink END, " +
                        "(SELECT m.message FROM Message m WHERE m.chat = sc.chat AND m.persistDate = (SELECT MAX(m2.persistDate) FROM Message m2 WHERE m2.chat = sc.chat)), " +
                        "(SELECT m.persistDate FROM Message m WHERE m.chat = sc.chat AND m.persistDate = (SELECT MAX(m2.persistDate) FROM Message m2 WHERE m2.chat = sc.chat)) " +
                        ")" +
                        "FROM com.javamentor.qa.platform.models.entity.chat.SingleChat sc " +
                        "WHERE sc.userOne.id = :id OR sc.useTwo.id = :id", SingleChatDto.class);
        query.setParameter("id", user.getId());
        return query.getResultList();
    }
}