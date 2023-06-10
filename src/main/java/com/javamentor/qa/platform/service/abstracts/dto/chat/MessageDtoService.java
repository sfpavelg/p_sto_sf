package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.MessageDto;

import java.util.List;
import java.util.Optional;

public interface MessageDtoService {

    List<MessageDto> getAllUserMessagesById(Long userId);

    Optional<Object> getMessageDtoById(Long messageId);
}
