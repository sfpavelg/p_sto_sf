package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.BlockChatUserListDao;
import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.user.BlockChatUserList;
import com.javamentor.qa.platform.service.abstracts.model.BlockChatUserListService;
import org.springframework.stereotype.Service;

@Service
public class BlockChatUserListServiceImpl extends ReadWriteServiceImpl<BlockChatUserList, Long> implements BlockChatUserListService {
    private final BlockChatUserListDao blockChatUserListDao;

    public BlockChatUserListServiceImpl(ReadWriteDao<BlockChatUserList, Long> readWriteDao, BlockChatUserListDao blockChatUserListDao) {
        super(readWriteDao);
        this.blockChatUserListDao = blockChatUserListDao;
    }

    @Override
    public void persist(BlockChatUserList blockChatUserList) {
        if (!blockChatUserListDao.isUserBlocked(blockChatUserList.getProfile().getId(), blockChatUserList.getBlocked().getId())) {
            super.persist(blockChatUserList);
        }
    }
}
