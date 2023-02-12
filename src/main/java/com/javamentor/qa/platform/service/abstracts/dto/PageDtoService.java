package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;

/**
 * Abstract class for creating paginated DTO
 *
 * @param <T>   The type of DTO.
 * @param <Map> Parameters for page selection.
 */
public abstract class PageDtoService<T, Map> {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    /**
     * @param params Parameters for pagination
     *               <p>
     *               Required parameters:
     *               <p>
     *               String DtoType -  Dto, which is used for pagination, for example - ExampleDTO.
     *               <p>
     *               int itemsOnPage - Specifying the number of elements on the page.
     *               <p>
     *               Optional parameters:
     *               <p>
     *               String DtoImpl - Name of the implementation from the DAO layer to be used.
     *               Contains the full name of the class, which begins with a small letter.
     *               <p>
     *               int totalPageCount - Specifying the number of pages.
     *               <p>
     *               int currentPageNumber - Specifying the page number to be given
     * @return Page of Dto's
     */
    protected PageDto<T> createPage(Map params) {
        HashMap<?, ?> paramToMap = (HashMap<?, ?>) params;
        PageDto<T> pageDto = new PageDto<>();
        String daoAbstractType = "com.javamentor.qa.platform.dao.abstracts.dto." + paramToMap.get("DtoType");
        String beanName = paramToMap.get("DtoImpl") != null ? (String) paramToMap.get("DtoImpl")
                : paramToMap.get("DtoType").toString().substring(0, 1).toLowerCase()
                + paramToMap.get("DtoType").toString().substring(1)
                + "Impl";

        try {
            Class<?> daoAbstractClazz = Class.forName(daoAbstractType);
            java.util.Map<String, ?> beansMap = context.getBeansOfType(daoAbstractClazz);
            Class<?> BeanClazz = beansMap.get(beanName).getClass();

            int totalResultCount = (int) BeanClazz
                    .getMethod("getTotalResultCount", java.util.Map.class)
                    .invoke(beansMap.get(beanName), paramToMap);
            List<T> listItems = (List<T>) BeanClazz
                    .getMethod("getItems", java.util.Map.class)
                    .invoke(beansMap.get(beanName), paramToMap);

            pageDto.setItemsOnPage(paramToMap.get("itemsOnPage") != null ? (int) paramToMap.get("itemsOnPage") : 10);
            pageDto.setTotalResultCount(totalResultCount);
            pageDto.setTotalPageCount(paramToMap.get("totalPageCount") != null
                    ? (int) paramToMap.get("totalPageCount")
                    : pageDto.getTotalResultCount() / pageDto.getItemsOnPage());
            pageDto.setCurrentPageNumber(paramToMap.get("currentPageNumber") != null
                    ? (int) paramToMap.get("currentPageNumber") : 0);
            pageDto.setItems(listItems);

        } catch (Exception ignore) {
        }

        return pageDto;
    }
}