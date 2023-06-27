package com.javamentor.qa.platform.service.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.SingleChatDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface SingleChatDtoService {
    List<SingleChatDto> getSingleChatDto(User user);
}
