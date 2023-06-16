package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Set;

@Repository
@Transactional
public class GroupChatDaoImpl extends ReadWriteDaoImpl<GroupChat, Long> implements GroupChatDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public Set<User> getUsers(GroupChat groupChat) {
        groupChat = entityManager.merge(groupChat);
        Set<User> users = groupChat.getUsers();
        Hibernate.initialize(groupChat.getUsers());
        return users;
    }

}
