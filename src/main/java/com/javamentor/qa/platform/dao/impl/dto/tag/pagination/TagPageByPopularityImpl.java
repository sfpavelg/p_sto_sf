package com.javamentor.qa.platform.dao.impl.dto.tag.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.pagination.TagPageByPopularity;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class TagPageByPopularityImpl implements TagPageByPopularity {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        return entityManager.createQuery(
                        "SELECT NEW com.javamentor.qa.platform.models.dto.tag.TagDto" +
                                "(tag.id, tag.name, tag.description) " +
                                "FROM Tag tag " +
                                "ORDER BY tag.questions.size DESC ",
                        TagDto.class)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(tag) FROM Tag tag");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
