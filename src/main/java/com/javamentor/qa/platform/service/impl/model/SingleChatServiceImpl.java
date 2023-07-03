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
        Optional<SingleChat> singleChat = Optional.ofNullable(singleChatDao.getSingleChatWithUsersById(chatId)
                .orElseThrow(() -> new NotFoundException("Пользователей нет в чате " + chatId)));
        if (singleChat.get().getUseTwo().getId() != userId && singleChat.get().getUserOne().getId() != userId) {
            throw new NotFoundException("Пользователя нет в чате " + chatId);
        }
        if (Objects.equals(singleChat.get().getUserOne().getId(), userId)) {
            SingleChat foundSingleChat = singleChat.get();
            foundSingleChat.setUserOne(null);
            singleChatDao.update(foundSingleChat);
        }
        SingleChat foundSingleChat = singleChat.get();
        foundSingleChat.setUseTwo(null);
        singleChatDao.update(foundSingleChat);
    }
}