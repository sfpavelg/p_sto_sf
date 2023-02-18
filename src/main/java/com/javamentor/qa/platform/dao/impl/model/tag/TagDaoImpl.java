package com.javamentor.qa.platform.dao.impl.model.tag;

import com.javamentor.qa.platform.dao.abstracts.model.tag.TagDao;
import com.javamentor.qa.platform.dao.impl.model.ReadWriteDaoImpl;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TagDaoImpl extends ReadWriteDaoImpl<Tag, Long> implements TagDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Tag> getTagsByName(List<String> names) {
        if (names != null && names.iterator().hasNext()) {
            return entityManager.createQuery("select t from Tag t WHERE t.name IN :names")
                    .setParameter("names", names).getResultList();
        }
        return new ArrayList<>();
    }
}
