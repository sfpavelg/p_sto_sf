package com.javamentor.qa.platform.service.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.impl.dto.chat.MessageDtoDaoImpl;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class MessageDtoServiceImpl extends PageDtoService<MessageDto> implements MessageDtoService {

    MessageDtoDaoImpl messageDtoDao;

    public MessageDtoServiceImpl(MessageDtoDaoImpl messageDtoDao, Map<String, PageDtoDao<MessageDto>> beansMap) {
        super(beansMap);
        this.messageDtoDao = messageDtoDao;
    }

    @Override
    public PageDto<MessageDto> getAllUserMessageDtoSortedByDate(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "paginationMessageDtoDaoImpl");
        return pageDto(param);
    }

    @Override
    public PageDto<MessageDto> getAllSingleChatMessagesSortedByPersistDate(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "paginationMessageDtoDaoBySingleChatImpl");
        return pageDto(param);
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