package com.javamentor.qa.platform.dao.impl.dto.chat.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.pagination.PaginationMessageDtoDaoBySingleChat;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class PaginationMessageDtoDaoBySingleChatImpl implements PaginationMessageDtoDaoBySingleChat {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        return entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.chat.MessageDto(" +
                        "m.id, " +
                        "m.message," +
                        "m.userSender.nickname, " +
                        "m.userSender.id, " +
                        "m.userSender.imageLink, " +
                        "m.persistDate) " +
                        "FROM Message m " +
                        "WHERE m.chat.id = (SELECT s.chat.id FROM SingleChat s WHERE s.chat.id = :chatId) " +
                        "ORDER BY m.persistDate DESC ", MessageDto.class)
                .setParameter("chatId", param.get("chatId"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager
                .createQuery("SELECT COUNT(m.chat.id) FROM Message m WHERE m.chat.id = :chatId")
                .setParameter("chatId", param.get("chatId")).getSingleResult());
    }
}

