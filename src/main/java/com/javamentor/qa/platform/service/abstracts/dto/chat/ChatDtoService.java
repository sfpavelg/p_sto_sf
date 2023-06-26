package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

import java.util.List;

public interface ChatDtoService {
    List<ChatDto> getChatDtoByUserIdAndValue(Long id, String value);
}
