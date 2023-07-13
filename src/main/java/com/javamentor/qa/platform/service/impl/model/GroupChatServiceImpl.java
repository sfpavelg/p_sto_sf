package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.GroupChatService;
import javassist.NotFoundException;
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
    public Set<User> getUsersFromGroupChatById(Long id) {
        return groupChatDao.getUsersFromGroupChatById(id);
    }

    @Override
    public void updateImage(Long chatId, String newImage) throws NotFoundException {
        if (!groupChatDao.existsById(chatId)) throw  new NotFoundException("Чат с таким id  не существует");
        groupChatDao.updateImage(chatId, newImage);
    }

}
