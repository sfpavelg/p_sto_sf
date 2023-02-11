package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExamplePaginationDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExamplePaginationPaginationDtoServiceImpl extends PageDtoService<ExampleDto, Map<String, Object>> implements ExamplePaginationDtoService {

    @Override
    public PageDto<ExampleDto> getListingUsers(HashMap<String, Object> param) throws NotFoundException {
        param.put("itemsOnPage", 10);
        param.put("DtoType", "ExampleDtoDao");
        // For sortBy default value is "id" sorting/
        param.put("sortBy", "id");

        PageDto<ExampleDto> page = createPage(param);

        if (page.getItems().isEmpty()) {
            throw new NotFoundException("Page with params not found");
        }
        return page;
    }

    @Override
    public PageDto<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) throws NotFoundException {
        param.put("itemsOnPage", 10);
        param.put("DtoType", "ExampleDtoDao");
        param.put("sortBy", "id");
        // Here we can define the class that will be used to call the methods
        // TODO: create a REAL anotherListingUsers
        param.put("DtoImpl", "exampleDtoDaoImpl");
        PageDto<ExampleDto> page = createPage(param);

        if (page.getItems().isEmpty()) {
            throw new NotFoundException("Page with params not found");
        }
        return page;
    }
}

