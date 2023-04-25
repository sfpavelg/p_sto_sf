package com.javamentor.qa.platform.service.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.tag.TagViewDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TagViewDtoServiceImpl extends PageDtoService<TagViewDto> implements TagViewDtoService {

    public TagViewDtoServiceImpl(Map<String, PageDtoDao<TagViewDto>> beansMap) {
        super(beansMap);
    }

    @Override
    public PageDto<TagViewDto> getSortedByDateTagList(HashMap<String, Object> params) {
        params.put("daoDtoImpl", "tagViewDtoDaoImpl");
        return pageDto(params);
    }

    @Override
    public PageDto<TagViewDto> getPageWithListTagDtoSortedByName(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "tagDtoPaginationSortedByNameDaoImpl");
        return pageDto(param);
    }

    @Override
    public PageDto<TagViewDto> getSortedByPopularity(HashMap<String, Object> params) {
        params.put("daoDtoImpl", "tagPageByPopularityImpl");
        return pageDto(params);
    }

    @Override
    public PageDto<TagViewDto> getPageWithListTagDtoSortedBySyllable(HashMap<String, Object> param) {
        param.put("daoDtoImpl", "tagDtoPaginationSortedBySyllableDaoImpl");
        return pageDto(param);
    }
}
