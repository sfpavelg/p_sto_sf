package com.javamentor.qa.platform.service.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.IgnoredTagDao;
import com.javamentor.qa.platform.dao.abstracts.model.ReadWriteDao;
import com.javamentor.qa.platform.dao.abstracts.model.TagDao;
import com.javamentor.qa.platform.models.entity.question.IgnoredTag;
import com.javamentor.qa.platform.models.entity.question.Tag;
import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.service.abstracts.model.IgnoredTagService;
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
    public void persistByTagId(Long id) {
        IgnoredTag ignoredTag = new IgnoredTag();
        Optional<Tag> tag = tagDao.getById(id);
        ignoredTag.setIgnoredTag(tag.orElse(null));
        ignoredTag.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        ignoredTagDao.persist(ignoredTag);
    }
}
