package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.ExampleDtoDao;
import com.javamentor.qa.platform.models.dto.ExampleDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class ExampleDtoDaoImplAnother implements ExampleDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<ExampleDto> getItems(Map<String, Object> paramToMap) {
        //For convenience, we will put the necessary parameters into variables
        int itemsOnPageParam = (int) paramToMap.get("itemsOnPage");
        int itemsPositionParam = (int) paramToMap.get("currentPageNumber") * itemsOnPageParam;

        Query query = entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.ExampleDto" +
                                "(u.id, u.email, u.fullName, u.city, u.imageLink, " +
                                "cast(coalesce(sum(r.count), 0) as integer )) " +
                                "from User u left join Reputation r " +
                                "with r.author.id = u.id where u.email like '%super%' group by u.id",
                        ExampleDto.class)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam);

        return (List<ExampleDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(u) FROM User u where u.email like '%super%'");
        return Integer.parseInt(query.getSingleResult().toString());
    }

}
