package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.chat.SingleChat;

import java.util.List;

public interface SingleChatDao extends ReadWriteDao<SingleChat, Long> {
    List<SingleChat> getSingleChatByUserId(Long Id, String value);
}