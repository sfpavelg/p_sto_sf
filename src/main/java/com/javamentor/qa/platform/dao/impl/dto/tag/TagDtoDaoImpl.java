package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.tag.RelatedTagsDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;


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
}
