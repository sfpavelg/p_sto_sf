package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExampleDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExampleDtoServiceImpl extends PageDtoService<ExampleDto, Map<String, Object>> implements ExampleDtoService {

    @Override
    public PageDto<ExampleDto> getListingUsers(HashMap<String, Object> param) throws NotFoundException {
        param.put("DtoType", "ExampleDtoDao");
        param.put("sortBy", "id");
        param.put("itemsOnPage", 10);

        PageDto<ExampleDto> page = PageDto(param);

        if (page.getItems().isEmpty()) {
            throw new NotFoundException("Page with params not found");
        }
        return page;
    }

    @Override
    public PageDto<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) throws NotFoundException {
        // Here we can define the class that will be used to call the methods, for example we use another dao
        // implementation where getting dto's for only superusers and specified items on page for 5 elems on page
        param.put("DtoImpl", "exampleDtoDaoImplAnother");

        param.put("DtoType", "ExampleDtoDao");
        param.put("itemsOnPage", 5);

        PageDto<ExampleDto> page = PageDto(param);

        if (page.getItems().isEmpty()) {
            throw new NotFoundException("Page with params not found");
        }
        return page;
    }
}

