package com.javamentor.qa.platform.dao.abstracts.dto;

import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

/**
 * Universal pagination for DTO's
 * @param <T> The type of DTO for which we perform pagination
 * @param <HashMap> Parameter for page selection
 */

@Repository
public interface PageDtoDao<T, HashMap> {

    /**
     * Creating a query to get a list of DTO for it
     */
    Query getItems(HashMap param);

    /**
     * Get the total number of values for this DTO
     */
    int getTotalResultCount(HashMap param);

    /**
     * Page constructor, by default, considers elements on page 10
     * @param param By default, the parameter is the page number
     * @param query The request to be paginated
     * @param itemsOnPage Count items on page
     * @param pageNumber Number of page that contains DTO's
     * @return Returns a paginated object for the DTO
     */
    default PageDto<T> createPage(HashMap param, Query query, int itemsOnPage, int pageNumber) {

        PageDto<T> page = new PageDto<>();
        page.setTotalResultCount(getTotalResultCount(param));
        page.setItemsOnPage(itemsOnPage);
        page.setTotalPageCount(page.getTotalResultCount() / page.getItemsOnPage());
        page.setCurrentPageNumber(pageNumber);
        page.setItems(paginateQuery(query, page));

        return page;
    }

    /**
     * Query constructor to build List<DTO>
     * @param query Query  to be paginated
     * @param page The object of the page from which the pagination parameters are taken
     * @return List of DTO's
     */
    default List<T> paginateQuery(Query query, PageDto<T> page) {
        return query
                .setMaxResults(page.getItemsOnPage())
                .setFirstResult(page.getCurrentPageNumber() * page.getItemsOnPage())
                .getResultList();

    }

}
