package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import javassist.NotFoundException;

import java.util.HashMap;

public interface ExampleDtoService {
    PageDto<ExampleDto> getListingUsers(HashMap<String, Object> param) throws NotFoundException;

    PageDto<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param) throws NotFoundException;
}
