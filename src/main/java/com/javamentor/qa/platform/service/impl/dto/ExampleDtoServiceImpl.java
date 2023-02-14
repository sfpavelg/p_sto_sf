package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExampleDtoService;
import com.javamentor.qa.platform.service.abstracts.dto.PageDtoService;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ExampleDtoServiceImpl extends PageDtoService<ExampleDto> implements ExampleDtoService {

    public ExampleDtoServiceImpl(Map<String, PageDtoDao<ExampleDto>> beansMap) {
        super(beansMap);
    }

    @Override
    public PageDto<ExampleDto> getListingUsers(HashMap<String, Object> param) throws NotFoundException {
        PageDto<ExampleDto> page;

        param.put("sortBy", "id");
        param.put("itemsOnPage", 10);

        try {
            page = pageDto(param);
            // Here we can catch exception when page not exists
            // or handle exception when there is no implementation in the DAO layer, but this should not be
        } catch (NotFoundException | RuntimeException e) {
            throw new NotFoundException("The page with " + param.get("currentPageNumber") + " number was not found");
        }

        return page;
    }

    @Override
    public PageDto<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) throws NotFoundException {
        PageDto<ExampleDto> page;

        // Here we can define the class that will be used to call the methods, for example we use another dao
        // implementation where getting dto's for only superusers and specified items on page for 5 elems on page
        param.put("daoDtoImpl", "exampleDtoDaoImplAnother");
        param.put("itemsOnPage", 5);


        try {
            page = pageDto(param);
        } catch (NotFoundException e) {
            throw new NotFoundException("The page with " + param.get("currentPageNumber") + " number was not found");
        }

        return page;
    }
}

