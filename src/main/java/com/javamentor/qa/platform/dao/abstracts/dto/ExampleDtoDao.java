package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.entity.user.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * A DTO for the {@link User} entity
 */
@Repository
public interface ExampleDtoDao extends PageDtoDao<ExampleDto, HashMap<String, Object>> {

    List<ExampleDto> getPaginatedUsers(HashMap<String, Object> param);
    List<ExampleDto> getAnotherPaginatedUsers(HashMap<String, Object> param);

}