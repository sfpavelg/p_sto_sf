package com.javamentor.qa.platform.service.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.SingleChatDtoDao;
import com.javamentor.qa.platform.models.dto.chat.SingleChatDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.SingleChatDtoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingleChatDtoServiceImpl implements SingleChatDtoService {

    private final SingleChatDtoDao singleChatDtoDao;

    public SingleChatDtoServiceImpl(SingleChatDtoDao singleChatDtoDao) {
        this.singleChatDtoDao = singleChatDtoDao;
    }

    @Override
    public List<SingleChatDto> getSingleChatDto(Long userId) {
        return singleChatDtoDao.getSingleChatDto(userId);
    }
}
