package com.javamentor.qa.platform.dao.impl.model;

import com.javamentor.qa.platform.dao.abstracts.model.TagDao;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class TagDaoImpl extends ReadWriteDaoImpl<Tag, Long> implements TagDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Map<String, Long> getAllTagNamesAndIds() {
        Map<String, Long> map = new HashMap<>();

        List<TagDto> list = em.createQuery("select new com.javamentor.qa.platform.models.dto.tag.TagDto(" +
                "t.id, t.name, t.description) from Tag t").getResultList();
        for (TagDto t : list) {
            map.put(t.getName(), t.getId());
        }
        return map;
    }
}
