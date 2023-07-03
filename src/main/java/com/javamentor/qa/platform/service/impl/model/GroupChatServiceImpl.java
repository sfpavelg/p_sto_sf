package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class GroupChatServiceImpl extends ReadWriteServiceImpl<GroupChat, Long> implements GroupChatService {
    private final GroupChatDao groupChatDao;

    public GroupChatServiceImpl(GroupChatDao groupChatDao) {
        super(groupChatDao);
        this.groupChatDao = groupChatDao;
    }

    @Override
    public Set<User> getUsersFromGroupChatById(Long id) {
        return groupChatDao.getUsersFromGroupChatById(id);
    }

    @Transactional
    @Override
    public void deleteUserFromChatById(Long chatId, Long userId) throws NotFoundException {
        GroupChat groupChat = groupChatDao.getGroupChatWithUsersById(chatId)
                .orElseThrow(() -> new NotFoundException("Пользователя нет в чате " + chatId));
        if (groupChat.getUsers().stream().noneMatch(u -> (u.getId() == userId))) {
            throw new NotFoundException("Пользователя нет в чате " + chatId);
        }
        Set<User> users = groupChat.getUsers().stream().filter(u -> (u.getId() != userId)).collect(Collectors.toSet());
        groupChat.setUsers(users);
        groupChatDao.update(groupChat);
    }
}
