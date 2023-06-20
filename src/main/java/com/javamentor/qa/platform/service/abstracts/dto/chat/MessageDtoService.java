package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import java.util.HashMap;

public interface MessageDtoService {
    PageDto<MessageDto> getMessagesBySingleChatIdOrderNew(HashMap<String, Object> param);
}
