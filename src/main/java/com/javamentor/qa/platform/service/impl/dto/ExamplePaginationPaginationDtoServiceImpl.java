package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExamplePaginationDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExamplePaginationPaginationDtoServiceImpl extends PageDtoService<ExampleDto, Map<?, ?>> implements ExamplePaginationDtoService {

    @Override
    public List<ExampleDto> getListingUsers(HashMap<String, Object> param) {
        PageDto<ExampleDto> page = createPage(param);
        return page.getItems();
    }

    @Override
    public List<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) {
        PageDto<ExampleDto> page = createPage(param);
        return page.getItems();
    }
}

