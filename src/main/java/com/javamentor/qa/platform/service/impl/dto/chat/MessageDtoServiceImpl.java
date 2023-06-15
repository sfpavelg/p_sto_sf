package com.javamentor.qa.platform.service.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.chat.MessageDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class MessageDtoServiceImpl extends PageDtoService<MessageDto> implements MessageDtoService {

    public MessageDtoServiceImpl(Map<String, PageDtoDao<MessageDto>> beansMap) {
        super(beansMap);
    }

    @Override
    public PageDto<MessageDto> getAllSingleChatMessagesSortedByPersistDate(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "paginationMessageDtoDaoBySingleChatImpl");
        return pageDto(param);
    }
}