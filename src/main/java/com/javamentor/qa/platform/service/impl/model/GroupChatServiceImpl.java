package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
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
        Optional <GroupChat> groupChat = Optional.ofNullable(groupChatDao.getGroupChatWithUsersById(chatId)
                .orElseThrow(() -> new NotFoundException("Пользователей нет в чате " + chatId)));

        if (groupChat.get().getUsers().stream().filter(u -> (u.getId() == userId)).collect(Collectors.toSet()).isEmpty()) {
            throw new NotFoundException("Пользователя нет в чате " + chatId);
        }
        GroupChat foundGroupChat = groupChat.get();
        Set<User> users = groupChat.get().getUsers().stream().filter(u -> (u.getId() != userId)).collect(Collectors.toSet());
        groupChat.get().setUsers(users);
        groupChatDao.update(foundGroupChat);
    }
}
