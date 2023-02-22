package com.javamentor.qa.platform.dao.abstracts.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.IgnoredTag;

public interface IgnoredTagDao extends ReadWriteDao<IgnoredTag, Long> {
    boolean existsByUserIdAndTagId (Long userId, Long tagId);
}
