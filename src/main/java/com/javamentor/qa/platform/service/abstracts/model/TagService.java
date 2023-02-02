package com.javamentor.qa.platform.service.abstracts.model;

import com.javamentor.qa.platform.models.entity.question.Tag;

import java.util.List;
import java.util.Map;

public interface TagService extends ReadWriteService<Tag, Long> {
    Map<String, Long> getAllTagNamesAndIds();
}
