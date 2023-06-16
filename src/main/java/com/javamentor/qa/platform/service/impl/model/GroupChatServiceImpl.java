package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class GroupChatServiceImpl extends ReadWriteServiceImpl<GroupChat, Long> implements GroupChatService {
    private final GroupChatDao groupChatDao;

    public GroupChatServiceImpl(GroupChatDao groupChatDao) {
        super(groupChatDao);
        this.groupChatDao = groupChatDao;
    }

    @Override
    public Set<User> getUsers(GroupChat groupChat) {
        return groupChatDao.getUsers(groupChat);
    }
}
