package com.javamentor.qa.platform.service.impl.dto.chat;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.ChatDtoDao;
import com.javamentor.qa.platform.models.dto.chat.ChatDto;
import com.javamentor.qa.platform.service.abstracts.dto.chat.ChatDtoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class ChatDtoServiceImpl implements ChatDtoService {

    private final ChatDtoDao chatDtoDao;

    public List<ChatDto> getChatDtoByUserIdAndValue(Long id, String value) {
        List <ChatDto> chatDtoFromSingleChat = chatDtoDao.getChatDtoFromSingleChatByUserIdAndValue(id,value);
        List <ChatDto> chatDtoFromGroupChat = chatDtoDao.getChatDtoFromGroupChatByUserIdAndValue(id,value);
        return Stream.of(chatDtoFromSingleChat, chatDtoFromGroupChat)
                .flatMap(Collection::stream)
                .sorted(Comparator.comparing(ChatDto::getPersistDateTimeLastMessage, Comparator.nullsFirst(Comparator.naturalOrder())).reversed())
                .collect(Collectors.toList());
    }
}
