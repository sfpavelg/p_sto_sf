package com.javamentor.qa.platform.service.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.tag.RelatedTagDao;
import com.javamentor.qa.platform.models.entity.question.RelatedTag;
import com.javamentor.qa.platform.service.abstracts.model.tag.RelatedTagService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RelatedTagServiceImpl extends ReadWriteServiceImpl<RelatedTag, Long> implements RelatedTagService {
    private final RelatedTagDao relatedTagDao;

    public RelatedTagServiceImpl(RelatedTagDao relatedTagDao) {
        super(relatedTagDao);
        this.relatedTagDao = relatedTagDao;
    }
}
