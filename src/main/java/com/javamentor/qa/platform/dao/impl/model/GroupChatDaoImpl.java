package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.question.QuestionViewed;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class GroupChatDaoImpl extends ReadWriteDaoImpl<GroupChat, Long> implements GroupChatDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<GroupChat> getGroupChatByUserId(Long id, String value) {
        Query query = entityManager.createQuery("SELECT gc FROM GroupChat gc JOIN FETCH gc.users " +
                        "AS gcc JOIN FETCH gc.chat AS gcChat JOIN FETCH gcc.role WHERE gcc.id = :userId" +
                        " AND gc.chat.title LIKE :searchValue")
                .setParameter("userId", id)
                .setParameter("searchValue", "%" + value + "%");
        return (List<GroupChat>) query.getResultList();
    }
}
