package com.javamentor.qa.platform.dao.abstracts.dto.chat;


import com.javamentor.qa.platform.models.dto.chat.MessageDto;

import java.util.List;
import java.util.Optional;


public interface MessageDtoDao {

    /**
     * Get a list of messages posted by same user
     *
     * @param userId Is id of user you need.
     * @return List of MessageDto
     */
    List<MessageDto> getAllUserMessages(Long userId);

    /**
     * Get message by id
     *
     * @param messageId Is a message id.
     * @return MessageDto
     */
    Optional<Object> getMessageDtoById(Long messageId);


}
