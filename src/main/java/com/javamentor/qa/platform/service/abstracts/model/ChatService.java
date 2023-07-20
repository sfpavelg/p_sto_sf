package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.Chat;
import javassist.NotFoundException;

public interface ChatService extends ReadWriteService<Chat, Long>{
    void deleteChatById(Long chatId) throws NotFoundException;

}
