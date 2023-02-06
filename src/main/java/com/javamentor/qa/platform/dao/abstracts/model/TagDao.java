package com.javamentor.qa.platform.dao.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.Map;

public interface TagDao extends ReadWriteDao<Tag, Long> {

    Map<String, Long> getAllTagNamesAndIds();
}
