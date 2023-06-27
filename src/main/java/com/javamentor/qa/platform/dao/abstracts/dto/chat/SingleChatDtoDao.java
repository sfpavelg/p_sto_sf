package com.javamentor.qa.platform.dao.abstracts.dto.chat;

import com.javamentor.qa.platform.models.dto.chat.SingleChatDto;
import com.javamentor.qa.platform.models.entity.user.User;

import java.util.List;

public interface SingleChatDtoDao {
    List<SingleChatDto> getSingleChatDto(User user);
}
