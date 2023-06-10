package com.javamentor.qa.platform.service.impl.chat;

import com.javamentor.qa.platform.dao.impl.dto.chat.MessageDtoDaoImpl;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageDtoServiceImpl implements MessageDtoService {

    MessageDtoDaoImpl messageDtoDao;

    public MessageDtoServiceImpl(MessageDtoDaoImpl messageDtoDao) {
        this.messageDtoDao = messageDtoDao;
    }

    @Override
    public List<MessageDto> getAllUserMessagesById(Long userId) {
        return messageDtoDao.getAllUserMessages(userId);
    }

    @Override
    public Optional<Object> getMessageDtoById(Long messageId) {
        return messageDtoDao.getMessageDtoById(messageId);
    }
}
