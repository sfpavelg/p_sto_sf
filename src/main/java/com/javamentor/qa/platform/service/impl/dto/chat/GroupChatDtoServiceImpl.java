package com.javamentor.qa.platform.service.impl.dto.chat;


import com.javamentor.qa.platform.dao.abstracts.dto.chat.GroupChatDtoDao;
import com.javamentor.qa.platform.models.dto.chat.GroupChatDto;
import com.javamentor.qa.platform.models.entity.chat.ChatType;
import com.javamentor.qa.platform.service.abstracts.dto.chat.GroupChatDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class GroupChatDtoServiceImpl implements GroupChatDtoService {

    private GroupChatDtoDao groupChatDtoDao;

    @Override
    public List<GroupChatDto> getGroupChatDto(Long id, ChatType group) {
        return groupChatDtoDao.getGroupChatDto(id, group);
    }
}
