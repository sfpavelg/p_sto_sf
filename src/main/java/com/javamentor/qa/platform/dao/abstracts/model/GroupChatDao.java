package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.GroupChat;

import java.util.List;

public interface GroupChatDao extends ReadWriteDao<GroupChat, Long> {
    List<GroupChat> getGroupChatByUserId (Long id, String value);
}
