package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

import java.util.Set;

public interface GroupChatDao extends ReadWriteDao<GroupChat, Long> {
    Set<User> getUsersFromGroupChatById(Long id);
    void updateImage(Long chatId, String newImage);
}
