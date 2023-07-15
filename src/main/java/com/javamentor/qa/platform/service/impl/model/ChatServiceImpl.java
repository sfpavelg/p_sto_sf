package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.ChatDao;
import com.javamentor.qa.platform.models.entity.chat.Chat;
import com.javamentor.qa.platform.service.abstracts.model.ChatService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChatServiceImpl extends ReadWriteServiceImpl<Chat, Long> implements ChatService{
    private final ChatDao chatDao;

    public ChatServiceImpl(ChatDao chatDao) {
        super(chatDao);
        this.chatDao = chatDao;
    }

    @Transactional
    public void deleteChatById(Long chatId) throws NotFoundException {
        if (!chatDao.existsById(chatId)) {
            throw new NotFoundException("Чат с таким id не найден");
        }
        chatDao.deleteById(chatId);
    }
}
