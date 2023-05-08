package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupBookmarkDao;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.GroupBookmarkService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GroupBookmarkServiceImpl extends ReadWriteServiceImpl<GroupBookmark, Long> implements GroupBookmarkService {
    private final GroupBookmarkDao groupBookmarkDao;

    public GroupBookmarkServiceImpl(GroupBookmarkDao groupBookmarkDao){
        super(groupBookmarkDao);
        this.groupBookmarkDao = groupBookmarkDao;
    }

    @Override
    @Transactional
    public GroupBookmark groupBookmarkPersist(User user, String groupName) throws NotFoundException {
        GroupBookmark groupBookmark = new GroupBookmark();
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        if(groupBookmarkDao.duplicateGroupBookmarkName(user.getId(), groupName)){
            throw new IllegalArgumentException("Title already exist");
        }
        groupBookmark.setUser(user);
        groupBookmark.setTitle(groupName);
        persist(groupBookmark);
        return groupBookmark;
    }
}