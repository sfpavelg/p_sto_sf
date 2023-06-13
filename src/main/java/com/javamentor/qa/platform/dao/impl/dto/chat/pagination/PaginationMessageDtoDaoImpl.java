package com.javamentor.qa.platform.dao.impl.dto.chat.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.pagination.PaginationMessageDtoDao;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class PaginationMessageDtoDaoImpl implements PaginationMessageDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageDto> getItems(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.chat.MessageDto(" +
                "m.id," +
                "m.message," +
                "m.userSender.nickname," +
                "m.userSender.id," +
                "m.userSender.imageLink," +
                "m.persistDate)" +
                "FROM Message m " +
                "WHERE m.userSender.id = :userId " +
                "ORDER BY m.persistDate DESC ", MessageDto.class).setParameter("userId", param.get("userId"));
        return (List<MessageDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager
                .createQuery("SELECT COUNT(m.userSender.id) FROM Message m WHERE m.userSender.id = :userId")
                .setParameter("userId", param.get("userId")).getSingleResult());
    }
}
