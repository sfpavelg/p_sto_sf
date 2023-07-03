package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.SingleChatDao;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import com.javamentor.qa.platform.service.abstracts.model.SingleChatService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;

@Service
public class SingleChatServiceImpl extends ReadWriteServiceImpl<SingleChat, Long> implements SingleChatService {
    private final SingleChatDao singleChatDao;

    public SingleChatServiceImpl(SingleChatDao singleChatDao) {
        super(singleChatDao);
        this.singleChatDao = singleChatDao;
    }

    @Transactional
    @Override
    public void deleteUserFromChatById(Long chatId, Long userId) throws NotFoundException {
        SingleChat singleChat = singleChatDao.getSingleChatWithUsersById(chatId)
                .orElseThrow(() -> new NotFoundException("Пользователя нет в чате " + chatId));
        if (singleChat.getUseTwo().getId() != userId && singleChat.getUserOne().getId() != userId) {
            throw new NotFoundException("Пользователя нет в чате " + chatId);
        }
        if (Objects.equals(singleChat.getUserOne().getId(), userId)) {
            singleChat.setUserOne(null);
            singleChatDao.update(singleChat);
        }
        singleChat.setUseTwo(null);
        singleChatDao.update(singleChat);
    }
}