package com.javamentor.qa.platform.service.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagDtoServiceImpl implements TagDtoService {

    private final TagDtoDao tagDtoDao;

    public TagDtoServiceImpl(TagDtoDao tagDtoDao) {
        this.tagDtoDao = tagDtoDao;
    }

    @Override
    public List<RelatedTagsDto> getRelatedTagsDto() {
        return tagDtoDao.getRelatedTagsDto();
    }

    @Override
    public Optional<TagDto> getById(Long id) {
        return tagDtoDao.getById(id);
    }

    @Override
    public List<TagDto> getIgnoredTagByUserId(Long id) {
        return tagDtoDao.getIgnoredTagByUserId(id);
    }

    @Override
    public List<TagDto> getTrackedTagsByUserId(Long userId) {
        return tagDtoDao.getTrackedTagsByUserId(userId);
    }

    @Override
    public List<TagDto> getTagsByName(String tagName) {
        return tagDtoDao.getTagsByName(tagName);
    }
}
