package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.GroupBookmark;
import com.javamentor.qa.platform.models.entity.user.User;
import javassist.NotFoundException;

public interface GroupBookmarkService extends ReadWriteService<GroupBookmark, Long> {
    GroupBookmark groupBookmarkPersist(User user, String groupName) throws NotFoundException;
}