package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface MessageDtoService {

    PageDto<MessageDto> getAllUserMessageDtoSortedByDate(HashMap<String, Object> param);
    List<MessageDto> getAllUserMessagesById(Long userId);

    Optional<Object> getMessageDtoById(Long messageId);

    PageDto<MessageDto> getAllSingleChatMessagesSortedByPersistDate(HashMap<String, Object> param);
}
