package com.javamentor.qa.platform.dao.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.ChatDto;

import java.util.List;

public interface ChatDtoDao {
    List<ChatDto> getChatDtoFromSingleChatByUserIdAndValue(Long id, String value);
    List<ChatDto> getChatDtoFromGroupChatByUserIdAndValue(Long id, String value);
}
