package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;

import java.util.HashMap;
import java.util.List;

public interface ExampleDtoService {
    List<ExampleDto> getListingUsers(HashMap<String, Object> param);

    List<ExampleDto> getAnotherListingUsers(HashMap<String, Object> param);
}
