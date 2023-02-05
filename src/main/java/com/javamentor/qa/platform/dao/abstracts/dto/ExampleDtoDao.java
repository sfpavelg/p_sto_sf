package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.entity.user.User;
import java.util.Map;

/**
 * A DTO for the {@link User} entity
 */
public interface ExampleDtoDao extends PageDtoDao<ExampleDto, Map<?, ?>> {
}