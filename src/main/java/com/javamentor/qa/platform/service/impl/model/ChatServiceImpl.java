package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ChatDao;
import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.dao.abstracts.model.SingleChatDao;
import com.javamentor.qa.platform.models.entity.chat.Chat;
import com.javamentor.qa.platform.models.entity.chat.ChatType;
import com.javamentor.qa.platform.service.abstracts.model.ChatService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatServiceImpl extends ReadWriteServiceImpl<Chat, Long> implements ChatService{
    private final ChatDao chatDao;
    private final SingleChatDao singleChatDao;
    private final GroupChatDao groupChatDao;

    public ChatServiceImpl(ChatDao chatDao, SingleChatDao singleChatDao, GroupChatDao groupChatDao) {
        super(chatDao);
        this.chatDao = chatDao;
        this.singleChatDao = singleChatDao;
        this.groupChatDao = groupChatDao;
    }

    @Transactional
    public void deleteChatById(Long chatId) throws NotFoundException {
        if (!chatDao.existsById(chatId)) {
            throw new NotFoundException("Чат с таким id не найден");
        }
        Chat chat = chatDao.getById(chatId).get();
        if (chat.getChatType() == ChatType.SINGLE) {
            singleChatDao.deleteById(chatId);
        } else {
            groupChatDao.deleteById(chatId);
        }
    }
}
