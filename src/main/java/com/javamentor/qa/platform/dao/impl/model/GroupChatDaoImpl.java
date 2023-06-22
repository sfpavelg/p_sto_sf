package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class GroupChatDaoImpl extends ReadWriteDaoImpl<GroupChat, Long> implements GroupChatDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Set<User> getUsersFromGroupChatById(Long id) {
        Query query = entityManager.createQuery("SELECT gc.users FROM GroupChat gc WHERE gc.id = :id")
                .setParameter("id", id);
        return (Set<User>) query.getResultList().stream().collect(Collectors.toSet());
    }

}
