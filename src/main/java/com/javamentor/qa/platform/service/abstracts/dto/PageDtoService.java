package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;

/**
 * Abstract class for creating paginated DTO
 *
 * @param <T>   The type of DTO
 * @param <Map> Parameters for page selection
 */
public abstract class PageDtoService<T, Map> {

    private PageDtoDao<T, Map> dao;

    /**
     * Setter for embedding the desired class from the DAO layer
     *
     * @param dao All DAO layers participating in the DTO layer are inherited from PageDTO
     */
    @Autowired
    public void setPageDtoDao(PageDtoDao<T, Map> dao) {
        this.dao = dao;
    }

    /**
     * The basic page constructor that takes as a parameter a Map with parameters and
     * uses a ready-made page assembly getPageDto method
     *
     * @param params Parameters containing information about pagination
     * @return Paginated PageDto with specific DTO
     */
    protected PageDto<T> createPage(Map params) {
        return getPageDto(params, dao);
    }

    /**
     * The overloaded page constructor can be used by passing an additional instance of the DAO layer to it,
     * use and overwrite it if you need to change the logic of page assembly
     *
     * @param params Parameters containing information about pagination
     * @param dao    Any instance of the DAO layer in which the getItems and getTotalResult methods are implemented
     * @return Paginated PageDto with specific DTO
     */
    protected PageDto<T> createPage(Map params, PageDtoDao<T, Map> dao) {
        return getPageDto(params, dao);
    }

    /**
     * Method that directly creates the page by parameters
     *
     * @param params Parameters containing information about pagination
     * @param dao    Any instance of the DAO layer in which the getItems and getTotalResult methods are implemented
     * @return Paginated PageDto with specific DTO
     */
    private PageDto<T> getPageDto(Map params, PageDtoDao<T, Map> dao) {
        HashMap<?, ?> paramToMap = (HashMap<?, ?>) params;
        PageDto<T> pageDto = new PageDto<>();

        pageDto.setItemsOnPage(paramToMap.get("itemsOnPage") != null ? (int) paramToMap.get("itemsOnPage") : 10);
        pageDto.setTotalResultCount(dao.getTotalResultCount(params));
        pageDto.setTotalPageCount(pageDto.getTotalResultCount() / pageDto.getItemsOnPage());
        pageDto.setCurrentPageNumber(paramToMap.get("currentPageNumber") != null
                ? (int) paramToMap.get("currentPageNumber") : 0);
        pageDto.setItems(dao.getItems(params));

        return pageDto;
    }
}
