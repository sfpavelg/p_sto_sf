package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ExampleDtoDao;
import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExampleDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExampleDtoServiceImpl extends PageDtoService<ExampleDto, Map<?, ?>> implements ExampleDtoService {

    private final ExampleDtoDao exampleDtoDao;

    public ExampleDtoServiceImpl(ExampleDtoDao exampleDtoDao) {
        this.exampleDtoDao = exampleDtoDao;
    }

    @Override
    public List<ExampleDto> getListingUsers(HashMap<String, Object> param) {
        PageDto<ExampleDto> page = createPage(param);
        return page.getItems();
    }

    @Override
    public List<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) {
        PageDto<ExampleDto> page = createPage(param, exampleDtoDao);
        return page.getItems();
    }


    @Override
    protected PageDto<ExampleDto> createPage(Map<?, ?> param, PageDtoDao<ExampleDto, Map<?, ?>> dao) {
        PageDto<ExampleDto> page = new PageDto<>();
        HashMap<String, Object> paramToMap = (HashMap<String, Object>) param;
        paramToMap.put("itemsOnPage", 3);
        page.setItemsOnPage((int) paramToMap.get("itemsOnPage"));
        page.setTotalResultCount(exampleDtoDao.getTotalResultCount(paramToMap));
        page.setTotalPageCount(page.getTotalResultCount() / page.getItemsOnPage());
        page.setCurrentPageNumber((int) paramToMap.get("currentPageNumber"));
        page.setItems(exampleDtoDao.getItems(paramToMap));

        return page;
    }
}
