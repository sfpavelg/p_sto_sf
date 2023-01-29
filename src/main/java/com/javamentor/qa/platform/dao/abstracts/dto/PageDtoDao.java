package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Universal pagination for DTO's
 * @param <T> The type of DTO for which we perform pagination
 * @param <P> Type of parameter for page selection
 */

@Repository
public interface PageDtoDao<T, P> {

    /**
     * Get the DTO list
     */
    List<T> getItems(P param);

    /**
     * Get the total number of values for this DTO
     */
    int getTotalResultCount(P param);

    /**
     * Page constructor, by default, considers elements on page 10
     * @param param By default, the parameter is the page number
     * @param query The request to be paginated
     * @return Returns a paginated object for the DTO
     */
    default PageDto<T> createPage(P param, Query query) {
        PageDto<T> page = new PageDto<>();
        page.setTotalResultCount(getTotalResultCount(param));
        page.setTotalPageCount(page.getTotalResultCount() / 10);
        page.setItemsOnPage(page.getTotalResultCount() / page.getTotalPageCount());
        page.setCurrentPageNumber(Integer.parseInt(param.toString()));
        List<T> listForPage = query
                .setMaxResults(page.getItemsOnPage())
                .setFirstResult(page.getCurrentPageNumber() * page.getItemsOnPage())
                .getResultList();
        page.setItems(listForPage);

        return page;
    }

}
