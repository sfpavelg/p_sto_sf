package com.javamentor.qa.platform.dao.abstracts.dto;

import java.util.List;
import java.util.Map;

/**
 * Universal pagination for DTO's
 *
 * @param <T>   The type of DTO for which we perform pagination
 */

public interface PageDtoDao<T> {

    /**
     * Get a list of DTO for creating PageDto and returning it on request
     *
     * @param param Is a parameters of pagination, such a "itemsOnPage", "currentPageNumber", etc.
     * @return Paginated List of DTO's
     */
    List<T> getItems(Map<String, Object> param);

    /**
     * Get the total number of values for this DTO
     *
     * @param param Is a parameters of pagination, such a "itemsOnPage", "currentPageNumber", etc. In this method
     *              it is possible to use values that limit the total number of DTO's.
     * @return Total number of available DTOs to display
     */
    int getTotalResultCount(Map<String, Object> param);


}
