package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoPaginationSortedByNameDao;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class TagDtoPaginationSortedByNameDaoImpl implements TagDtoPaginationSortedByNameDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        Query query = entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.tag.TagDto" +
                                "(tag.id, tag.name, tag.description)  " +
                                "FROM Tag tag ORDER BY tag.name",
                        TagDto.class)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam);
        return (List<TagDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(tag) FROM Tag tag ");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
