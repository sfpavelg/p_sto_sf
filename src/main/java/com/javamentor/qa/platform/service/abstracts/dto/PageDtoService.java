package com.javamentor.qa.platform.service.abstracts.dto;

import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.HashMap;
import java.util.List;

/**
 * Abstract class for creating paginated DTO
 *
 * @param <T>   The type of DTO
 * @param <Map> Parameters for page selection
 */
public abstract class PageDtoService<T, Map> {

    private ApplicationContext context;

    @Autowired
    public void setContext(ApplicationContext context) {
        this.context = context;
    }


    protected PageDto<T> createPage(Map params) {
        HashMap<?, ?> paramToMap = (HashMap<?, ?>) params;
        PageDto<T> pageDto = new PageDto<>();
        String daoAbstractType = "com.javamentor.qa.platform.dao.abstracts.dto." + paramToMap.get("DtoType");
        String beanName = paramToMap.get("DtoImpl") != null ? (String) paramToMap.get("DtoImpl")
                : paramToMap.get("DtoType").toString().substring(0, 1).toLowerCase()
                + paramToMap.get("DtoType").toString().substring(1)
                + "Impl";
// TODO: Maybe don't ignore exceptions?
        try {
            Class<?> daoAbstractClass = Class.forName(daoAbstractType);
            java.util.Map<String, ?> beansMap = context.getBeansOfType(daoAbstractClass);
            Class<?> BeanClazz = beansMap.get(beanName).getClass();

            int totalResultCount = (int) BeanClazz
                    .getMethod("getTotalResultCount", java.util.Map.class)
                    .invoke(beansMap.get(beanName), paramToMap);
            List<T> listItems = (List<T>) BeanClazz
                        .getMethod("getItems", java.util.Map.class)
                        .invoke(beansMap.get(beanName), paramToMap);

            pageDto.setItemsOnPage(paramToMap.get("itemsOnPage") != null ? (int) paramToMap.get("itemsOnPage") : 10);
            pageDto.setTotalResultCount(totalResultCount);
            pageDto.setTotalPageCount(pageDto.getTotalResultCount() / pageDto.getItemsOnPage());
            pageDto.setCurrentPageNumber(paramToMap.get("currentPageNumber") != null
                    ? (int) paramToMap.get("currentPageNumber") : 0);
            pageDto.setItems(listItems);

        } catch (Exception ignore) {
        }

        return pageDto;
    }
}

//            ClassPathScanningCandidateComponentProvider scanner = new ClassPathScanningCandidateComponentProvider(false);
//            scanner.addIncludeFilter(new AssignableTypeFilter(ExampleDtoDao.class));
//            Set<BeanDefinition> beans = scanner.findCandidateComponents("com.javamentor.qa.platform.dao.impl.dto");
//            Iterator<BeanDefinition> itr = beans.iterator();