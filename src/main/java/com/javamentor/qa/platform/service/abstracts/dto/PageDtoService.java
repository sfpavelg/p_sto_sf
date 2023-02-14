package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.models.dto.PageDto;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Abstract class for creating paginated DTO
 *
 * @param <T> The type of DTO.
 */
public abstract class PageDtoService<T> {

    private final Map<String, PageDtoDao<T>> beansMap;

    @Autowired
    public PageDtoService(Map<String, PageDtoDao<T>> beansMap) {
        this.beansMap = beansMap;
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
    protected PageDto<T> pageDto(Map<String, Object> params) throws NullPointerException, NotFoundException {
        PageDto<T> pageDto = new PageDto<>();

        if (beansMap.isEmpty()) {
            throw new RuntimeException("There are no implementations available in the DAO layer");
        }

        String beanName = params.get("daoDtoImpl") != null ? (String) params.get("daoDtoImpl")
                : (String) beansMap.keySet().toArray()[0];

        pageDto.setTotalResultCount(beansMap.get(beanName).getTotalResultCount(params));
        pageDto.setItemsOnPage(params.get("itemsOnPage") != null ? (int) params.get("itemsOnPage") : 10);
        pageDto.setTotalPageCount(params.get("totalPageCount") != null
                ? (int) params.get("totalPageCount")
                : pageDto.getTotalResultCount() / pageDto.getItemsOnPage());
        pageDto.setCurrentPageNumber(params.get("currentPageNumber") != null
                ? (int) params.get("currentPageNumber") : 0);
        pageDto.setItems(beansMap.get(beanName).getItems(params));

        if (pageDto.getTotalResultCount() < 0 | pageDto.getItemsOnPage() < 0
                | pageDto.getTotalPageCount() < 0 | pageDto.getCurrentPageNumber() < 0
                | pageDto.getItems().isEmpty()) {
            throw new NotFoundException("The page with the specified parameters was not found");
        }

        return pageDto;
    }
}