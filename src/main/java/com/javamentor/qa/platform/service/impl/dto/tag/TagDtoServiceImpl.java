package com.javamentor.qa.platform.service.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagDtoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TagDtoServiceImpl extends PageDtoService<TagDto> implements TagDtoService {

    private final TagDtoDao tagDtoDao;

    public TagDtoServiceImpl(TagDtoDao tagDtoDao, Map<String, PageDtoDao<TagDto>> beansMap) {
        super(beansMap);
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

}
