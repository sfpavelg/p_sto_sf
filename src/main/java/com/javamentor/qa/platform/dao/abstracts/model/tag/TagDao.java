package com.javamentor.qa.platform.dao.abstracts.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.List;

public interface TagDao extends ReadWriteDao<Tag, Long> {

    List<Tag> getTagsByName(List<String> names);
}
