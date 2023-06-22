package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.Set;

import java.util.List;

public interface GroupChatDao extends ReadWriteDao<GroupChat, Long> {

    Set<User> getUsersFromGroupChatById(Long id);
    List<GroupChat> getGroupChatByUserId (Long id, String value);
}
