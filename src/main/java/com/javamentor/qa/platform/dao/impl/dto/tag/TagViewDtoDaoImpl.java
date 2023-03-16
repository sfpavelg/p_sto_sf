package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagViewDtoDao;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;

@Repository
public class TagViewDtoDaoImpl implements TagViewDtoDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagViewDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber");
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime today =  now.minusHours(Long.valueOf(24));
        LocalDateTime weekBefore =  now.minusDays(Long.valueOf(7));

        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.tag.TagViewDto(" +
                        "t.id, " +
                        "t.name," +
                        "t.description," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id)," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id and q.persistDateTime between :today and :now)," +
                        "(select count (q.id) from Question q join q.tags qt where t.id = qt.id and q.persistDateTime between :weekBefore and :now))" +
                        "from Tag t order by t.persistDateTime desc")
                .setParameter("today", today)
                .setParameter("weekBefore", weekBefore)
                .setParameter("now", now)
                .setFirstResult(itemsPositionParam <= 1 ? itemsPositionParam -1: itemsPositionParam)
                .setMaxResults(itemsOnPageParam);



        return (List<TagViewDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(p) FROM Tag p ");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
