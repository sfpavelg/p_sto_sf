package com.javamentor.qa.platform.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageDto<T> {
    private int currentPageNumber;
    private int totalPageCount;
    private int totalResultCount;
    private List<T> items;
    private int itemsOnPage;

}
