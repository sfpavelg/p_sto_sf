package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupBookmarkDao;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import com.javamentor.qa.platform.service.abstracts.model.GroupBookmarkService;
import org.springframework.stereotype.Service;

@Service
public class GroupBookmarkServiceImpl extends ReadWriteServiceImpl<GroupBookmark, Long> implements GroupBookmarkService {
    public GroupBookmarkServiceImpl(GroupBookmarkDao groupBookmarkDao){
        super(groupBookmarkDao);
    }
}