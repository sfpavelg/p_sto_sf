package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import javassist.NotFoundException;

public interface SingleChatService extends ReadWriteService<SingleChat, Long> {

    void deleteUserFromChatById(Long chatId, Long userId) throws NotFoundException;
}
