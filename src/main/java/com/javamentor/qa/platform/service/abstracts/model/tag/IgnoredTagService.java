package com.javamentor.qa.platform.service.abstracts.model.tag;

import com.javamentor.qa.platform.models.entity.question.IgnoredTag;
import com.javamentor.qa.platform.service.abstracts.model.ReadWriteService;
import javassist.NotFoundException;

public interface IgnoredTagService extends ReadWriteService<IgnoredTag, Long> {
    void persistByTagId(Long id) throws NotFoundException;
}
