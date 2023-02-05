package com.javamentor.qa.platform.dao.abstracts.dto;

import java.util.List;

/**
 * Universal pagination for DTO's
 *
 * @param <T>   The type of DTO for which we perform pagination
 * @param <Map> Parameters for page selection
 */

public interface PageDtoDao<T, Map> {

    /**
     * Get a list of DTO for creating PageDto and returning it on request
     *
     * @param param Is a parameters of pagination, such a "itemsOnPage", "currentPageNumber", etc.
     * @return Paginated List of DTO's
     */
    List<T> getItems(Map param);

    /**
     * Get the total number of values for this DTO
     *
     * @param param Is a parameters of pagination, such a "itemsOnPage", "currentPageNumber", etc. In this method
     *              it is possible to use values that limit the total number of DTO's.
     * @return Total number of available DTOs to display
     */
    int getTotalResultCount(Map param);


}
