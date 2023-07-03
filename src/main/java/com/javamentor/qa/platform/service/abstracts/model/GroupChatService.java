package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

import java.util.Set;

public interface GroupChatService extends ReadWriteService<GroupChat, Long> {

    Set<User> getUsersFromGroupChatById(Long id);

    void deleteUserFromChatById(Long chatId, Long userId) throws NotFoundException;
}
