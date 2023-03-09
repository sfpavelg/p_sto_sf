package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.PageDtoDao;
import com.javamentor.qa.platform.exception.PaginationDtoException;
import com.javamentor.qa.platform.exception.PaginationDtoIncorrectParametersException;
import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Abstract class for creating paginated DTO
 *
 * @param <T> The type of DTO.
 */
public class PageDtoService<T> {

    private final Map<String, PageDtoDao<T>> beansMap;

    @Autowired
    public PageDtoService(Map<String, PageDtoDao<T>> beansMap) {
        this.beansMap = beansMap;
    }


    /**
     * @param params Parameters for pagination:
     *               <p>
     *               String daoDtoImpl - Name of the implementation from the DAO layer to be used.
     *               Contains the full name of the class, which begins with a small letter. Example: exampleDtoDaoImpl.
     *               <p>
     *               int itemsOnPage - Specifying the number of elements on the page.
     *               <p>
     *               int currentPageNumber - Specifying the page number to be given
     *               <p>
     * @return Page of Dto's
     */
    protected PageDto<T> pageDto(Map<String, Object> params) {
        PageDto<T> pageDto = new PageDto<>();
        String beanName = (String) params.get("daoDtoImpl");

        checkParams(params);

        pageDto.setTotalResultCount(beansMap.get(beanName).getTotalResultCount(params));
        pageDto.setItemsOnPage((int) params.get("itemsOnPage"));
        pageDto.setTotalPageCount((pageDto.getTotalResultCount() + pageDto.getItemsOnPage() - 1) / pageDto.getItemsOnPage());
        pageDto.setCurrentPageNumber((int) params.get("currentPageNumber"));
        pageDto.setItems(beansMap.get(beanName).getItems(params));

        return pageDto;
    }

    private void checkParams(Map<String, Object> params) {
        if (params.get("daoDtoImpl") == null) {
            throw new PaginationDtoException("You must specify the implementation");
        }

        if (params.get("itemsOnPage") == null) {
            throw new PaginationDtoIncorrectParametersException("The parameter of the number of elements on the page was not found");
        }

        if ((int) params.get("itemsOnPage") < 1) {
            throw new PaginationDtoIncorrectParametersException("The number of elements on the page cannot be less than 1");
        }

        if (params.get("currentPageNumber") == null) {
            throw new PaginationDtoIncorrectParametersException("Page number parameter not found");
        }

        if ((int) params.get("currentPageNumber") < 1) {
            throw new PaginationDtoIncorrectParametersException("The current page cannot be less than 1");
        }

        if (beansMap.get((String) params.get("daoDtoImpl")) == null) {
            throw new PaginationDtoException("The specified implementation does not exist");
        }

    }
}