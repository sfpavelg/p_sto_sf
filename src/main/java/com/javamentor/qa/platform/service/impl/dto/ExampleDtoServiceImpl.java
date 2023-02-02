package com.javamentor.qa.platform.service.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ExampleDtoDao;
import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.service.abstracts.dto.ExampleDtoService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExampleDtoServiceImpl implements ExampleDtoService {

    private final ExampleDtoDao exampleDtoDao;

    public ExampleDtoServiceImpl(ExampleDtoDao exampleDtoDao) {
        this.exampleDtoDao = exampleDtoDao;
    }

    @Override
    public List<ExampleDto> getListingUsers(HashMap<String, Object> param) {
        return exampleDtoDao.getPaginatedUsers(param);
    }

    @Override
    public List<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) {
        return exampleDtoDao.getAnotherPaginatedUsers(param);
    }


}
