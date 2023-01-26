package com.javamentor.qa.platform.dao.impl.dto;

import com.javamentor.qa.platform.dao.abstracts.dto.TagDtoDao;
import com.javamentor.qa.platform.models.dto.question.TagDto;
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
    public Optional<List<TagDto>> getTagDtoById(Long id) {
        Query query = entityManager.
                createQuery("select t.id, t.name, t.description from Tag t join t.questions as tq  " +
                        "where tq.id = :id ").
                setParameter("id", id);
        return Optional.of((List<TagDto>) query.getResultList());
    }
}
