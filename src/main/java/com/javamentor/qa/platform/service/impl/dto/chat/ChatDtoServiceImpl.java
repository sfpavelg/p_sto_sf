package com.javamentor.qa.platform.service.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.model.GroupChatDao;
import com.javamentor.qa.platform.dao.abstracts.model.MessageDao;
import com.javamentor.qa.platform.dao.abstracts.model.SingleChatDao;
import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.chat.Message;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.dto.chat.ChatDtoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ChatDtoServiceImpl implements ChatDtoService {

    private final SingleChatDao singleChatDao;
    private final GroupChatDao groupChatDao;
    private final MessageDao messageDao;

    @Override
    public List<ChatDto> getAllUserChatsByValue(User user, String value) throws NotFoundException {
        List<ChatDto> chatDtoList = new ArrayList<>();
        List<GroupChat> groupChatList = groupChatDao.getGroupChatByUserId(user.getId(), value);
        List<SingleChat> singleChatList = singleChatDao.getSingleChatByUserId(user.getId(), value);
        //GroupChat собираются в ChatDto
        for (GroupChat groupChat : groupChatList) {
            ChatDto chatDto = new ChatDto();
            Message lastMessage = messageDao.getLastMessageByChatId(groupChat.getChat().getId());
            chatDto.setId(groupChat.getChat().getId());
            chatDto.setName(groupChat.getChat().getTitle());
            chatDto.setImage(null);
            if (lastMessage != null) {
                chatDto.setPersistDateTimeLastMessage(lastMessage.getPersistDate());
                chatDto.setLastMessage(lastMessage.getMessage());
            }
            chatDtoList.add(chatDto);
        }
        //SingleChat собираются в ChatDto
        for (SingleChat singleChat : singleChatList) {
            ChatDto chatDto = new ChatDto();
            Message lastMessage = messageDao.getLastMessageByChatId(singleChat.getChat().getId());
            chatDto.setId(singleChat.getChat().getId());
            if (user.getNickname().equals(singleChat.getUserOne().getNickname())) {
                chatDto.setName(singleChat.getUseTwo().getNickname());
                chatDto.setImage(singleChat.getUseTwo().getImageLink());
            }
            chatDto.setName(singleChat.getUserOne().getNickname());
            chatDto.setImage(singleChat.getUserOne().getImageLink());
            if (lastMessage != null) {
                chatDto.setLastMessage(lastMessage.getMessage());
                chatDto.setPersistDateTimeLastMessage(lastMessage.getPersistDate());
            }
            chatDtoList.add(chatDto);
        }
        return chatDtoList;
    }
}
