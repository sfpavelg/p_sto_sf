package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.GroupBookmark;

public interface GroupBookmarkDao extends ReadWriteDao<GroupBookmark, Long> {
boolean duplicateGroupBookmarkName(Long userId, String groupName);
}