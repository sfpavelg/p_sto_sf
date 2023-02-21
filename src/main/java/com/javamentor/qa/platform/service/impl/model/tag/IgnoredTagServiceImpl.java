package com.javamentor.qa.platform.service.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.tag.IgnoredTagDao;
import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.dao.abstracts.model.tag.TagDao;
import com.javamentor.qa.platform.models.entity.question.IgnoredTag;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.tag.IgnoredTagService;
import com.javamentor.qa.platform.service.impl.model.ReadWriteServiceImpl;
import javassist.NotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class IgnoredTagServiceImpl extends ReadWriteServiceImpl<IgnoredTag, Long> implements IgnoredTagService {
    private final IgnoredTagDao ignoredTagDao;
    private final TagDao tagDao;

    public IgnoredTagServiceImpl(ReadWriteDao<IgnoredTag, Long> readWriteDao, IgnoredTagDao ignoredTagDao, TagDao tagDao) {
        super(readWriteDao);
        this.ignoredTagDao = ignoredTagDao;
        this.tagDao = tagDao;
    }

    @Override
    @Transactional
    public void persistByTagId(Long id) throws NotFoundException {
        IgnoredTag ignoredTag = new IgnoredTag();
        Optional<Tag> tag = tagDao.getById(id);
        if (tag.isEmpty()) {
            throw new NotFoundException("Tag with id = " + id + " not found");
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (ignoredTagDao.existsByUserIdAndTagId(user.getId(), id)) {
            throw new IllegalArgumentException("IgnoredTag with id = " + id + " already added by user with id = " + user.getId());
        }
        ignoredTag.setIgnoredTag(tag.get());
        ignoredTag.setUser(user);
        ignoredTagDao.persist(ignoredTag);
    }
}
