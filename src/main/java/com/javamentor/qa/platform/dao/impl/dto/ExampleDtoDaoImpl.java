package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ExampleDtoDao;
import com.javamentor.qa.platform.models.dto.ExampleDto;
import com.javamentor.qa.platform.models.dto.PageDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;

@Repository
public class ExampleDtoDaoImpl implements ExampleDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Query getItems(HashMap<String, Object> param) {
        // Here we're writing a query that will be paginated and also return a list of DTO.
        // We're using param "sortBy" in getAnotherPaginatedUsers() method, but in getPaginatedUsers()
        // it by default is "id"
        return entityManager.createQuery(
                "SELECT new com.javamentor.qa.platform.models.dto.ExampleDto" +
                        "(u.id, u.email, u.fullName, u.city, u.imageLink, " +
                        "cast(coalesce(sum(r.count), 0) as integer )) " +
                        "from User u left join Reputation r " +
                        "with r.author.id = u.id group by u.id order by u." + param.get("sortBy") + " ASC",
                ExampleDto.class);
    }

    @Override
    public int getTotalResultCount(HashMap<String, Object> param) {
        //  Query that returns the number of all DTO's
        Query query = entityManager.createQuery("SELECT COUNT(p) FROM User p ");
        return Integer.parseInt(query.getSingleResult().toString());
    }


    @Override
    public List<ExampleDto> getPaginatedUser(HashMap<String, Object> param) {
        // Simplest way to use pagination is using createPage() method which accepts parameters
        PageDto<ExampleDto> page = createPage(param, getItems(param),
                (int) param.get("itemsOnPage"), (int) param.get("pageNumber"));

        return page.getItems();
    }

    @Override
    public List<ExampleDto> getAnotherPaginatedUser(HashMap<String, Object> param) {
        // Another way to use pagination is ignoring createPage() method and creating PageDto
        // by filling in the fields yourself, and if necessary, paginating the request yourself (paginateQuery).
        int pageNumber = (int) param.get("pageNumber");

        PageDto<ExampleDto> page = new PageDto<>();
        page.setCurrentPageNumber(pageNumber);
        page.setTotalResultCount(getTotalResultCount(param));
        page.setItemsOnPage(4);
        page.setTotalPageCount(page.getTotalResultCount() / page.getItemsOnPage());
        page.setItems(paginateQuery(getItems(param), page));

        return page.getItems();
    }
}
