package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.dto.tag.TagViewDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;


@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<TagDto> getTagDtoById(Long id) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.tag.TagDto(" +
                        "t.id, " +
                        "t.name, " +
                        "t.description) " +
                        "from Tag t join t.questions as tq where tq.id = :id")
                .setParameter("id", id);
        return (List<TagDto>) query.getResultList();
    }

    @Override
    public List<RelatedTagsDto> getRelatedTagsDto() {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto(" +
                "t.id as id, " +
                "t.name as title, " +
                "(select count (q.id) from Question q join q.tags qt where t.id = qt.id) as countQuestion) " +
                "from Tag t order by countQuestion desc ").setMaxResults(10);
        return (List<RelatedTagsDto>) query.getResultList();
    }

    @Override
    public Optional<TagDto> getById(Long id) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.tag.TagDto(" +
                        "t.id, " +
                        "t.name, " +
                        "t.description) " +
                        "from Tag t where t.id = :id")
                .setParameter("id", id);
        return SingleResultUtil.getSingleResultOrNull(query);
    }

    @Override
    public List<TagDto> getIgnoredTagByUserId(Long id) {
        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.tag.TagDto(" +
                        "t.id, " +
                        "t.name) " +
                        "from Tag t join IgnoredTag it on t.id = it.ignoredTag.id where it.user.id = :id")
                .setParameter("id", id);
        return (List<TagDto>) query.getResultList();
    }

    @Override
    public List<TagViewDto> getSortedByDateTagList(int items, int page) {
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
                .setParameter("now", now);

        query.setFirstResult((page -1) * items);
        query.setMaxResults(items);

        return (List<TagViewDto>) query.getResultList();
    }


}
