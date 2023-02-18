package com.javamentor.qa.platform.service.abstracts.model.tag;

import com.javamentor.qa.platform.models.entity.question.TrackedTag;
import com.javamentor.qa.platform.service.abstracts.model.ReadWriteService;
import javassist.NotFoundException;

public interface TrackedTagService extends ReadWriteService<TrackedTag, Long> {
    void persistByTagId(Long id) throws NotFoundException;
}
