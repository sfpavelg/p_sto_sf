package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.GroupBookmarkDao;
import com.javamentor.qa.platform.models.entity.GroupBookmark;
import org.springframework.stereotype.Repository;

@Repository
public class GroupBookmarkDaoImpl extends ReadWriteDaoImpl<GroupBookmark, Long> implements GroupBookmarkDao{
}