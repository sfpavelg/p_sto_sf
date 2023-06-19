package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.Message;

import java.time.LocalDateTime;
import java.util.List;

public interface MessageDao extends ReadWriteDao<Message, Long> {
    Message getLastMessageByChatId(Long id);
}
