package com.javamentor.qa.platform.dao.impl.dto.tag.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.pagination.TagPageByPopularity;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class TagPageByPopularityImpl implements TagPageByPopularity {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagViewDto> getItems(Map<String, Object> param) {

        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today = now.minusHours(24L);
        LocalDateTime weekBefore = now.minusDays(7L);

        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.tag.TagViewDto(" +
                        "t.id, " +
                        "t.name," +
                        "t.description," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id)," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id and q.persistDateTime between :today and :now)," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id and q.persistDateTime between :weekBefore and :now))" +
                        "from Tag t order by t.questions.size DESC")
                .setParameter("today", today)
                .setParameter("weekBefore", weekBefore)
                .setParameter("now", now)
                .setFirstResult(itemsPositionParam)
                .setMaxResults(itemsOnPageParam);

        return (List<TagViewDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(tag) FROM Tag tag");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
