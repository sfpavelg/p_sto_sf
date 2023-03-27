package com.javamentor.qa.platform.dao.impl.dto.tag.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.pagination.TagDtoPaginationSortedBySyllableDao;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class TagDtoPaginationSortedBySyllableDaoImpl implements TagDtoPaginationSortedBySyllableDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        return entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.tag.TagDto" +
                                "(tag.id, tag.name, tag.description)  " +
                                "FROM Tag tag WHERE tag.name LIKE CONCAT('%', :syllable,'%') " +
                                "ORDER BY tag.name ",
                        TagDto.class)
                .setParameter("syllable", param.get("syllable"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager.createQuery(
                        "SELECT COUNT(tag) FROM Tag tag " +
                                "WHERE tag.name LIKE CONCAT('%', :syllable,'%')")
                .setParameter("syllable", param.get("syllable"))
                .getSingleResult());
    }
}
