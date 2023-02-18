package com.javamentor.qa.platform.service.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.dao.abstracts.model.tag.TagDao;
import com.javamentor.qa.platform.dao.abstracts.model.tag.TrackedTagDao;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.question.TrackedTag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.tag.TrackedTagService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TrackedTagServiceImpl extends ReadWriteServiceImpl<TrackedTag, Long> implements TrackedTagService {

    private final TrackedTagDao trackedTagDao;
    private final TagDao tagDao;

    public TrackedTagServiceImpl(ReadWriteDao<TrackedTag, Long> readWriteDao, TrackedTagDao trackedTagDao, TagDao tagDao) {
        super(readWriteDao);
        this.trackedTagDao = trackedTagDao;
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public void persistByTagId(Long id) throws NotFoundException {
        TrackedTag trackedTag = new TrackedTag();
        Optional<Tag> tag = tagDao.getById(id);
        if (tag.isEmpty()) {
            throw new NotFoundException("Tag with id = " + id + " not found");
        }
        trackedTag.setTrackedTag(tag.get());
        trackedTag.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        trackedTagDao.persist(trackedTag);
    }
}
