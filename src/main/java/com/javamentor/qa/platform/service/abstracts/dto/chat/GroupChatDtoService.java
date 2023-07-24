package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.GroupChatDto;
import com.javamentor.qa.platform.models.entity.chat.ChatType;

import java.util.List;

public interface GroupChatDtoService {

    List<GroupChatDto> getGroupChatDto(Long id, ChatType group);
}
