package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserDto> getById(Long id) {

        return SingleResultUtil.getSingleResultOrNull(
                entityManager.createQuery(
                                "SELECT new com.javamentor.qa.platform.models.dto.user.UserDto" +
                                        "(u.id, u.email, u.fullName, u.city, u.imageLink, " +
                                        "cast(coalesce(sum(r.count), 0) as integer )) " +
                                        "from User u left join Reputation r " +
                                        "with r.author.id = u.id where u.id = :id group by u.id",
                                UserDto.class)
                        .setParameter("id", id));
    }

    @Override
    public List<UserDto> getItems(Map<String, Object> paramToMap) {
        int itemsOnPageParam = (int) paramToMap.get("itemsOnPage");
        int itemsPositionParam = (int) paramToMap.get("currentPageNumber") * itemsOnPageParam;
        String sortParam = (String) paramToMap.get("sortBy");
        Query query = entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.user.UserDto" +
                                "(u.id, u.email, u.fullName, u.city, u.imageLink, " +
                                "cast(coalesce(sum(r." + sortParam + "), 0) as integer )) " +
                                "from User u left join Reputation r with r.author.id = u.id " +
                                "group by u.id " +
                                "order by cast(coalesce(sum(r." + sortParam + "), 0) as integer ) ASC",
                        UserDto.class)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam);
        return (List<UserDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(u) FROM User u ");
        return Integer.parseInt(query.getSingleResult().toString());
    }

}
