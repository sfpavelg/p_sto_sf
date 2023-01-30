package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.TagDtoDao;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import com.javamentor.qa.platform.models.entity.question.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TagDtoDaoImpl implements TagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final ModelMapper modelMapper = new ModelMapper();


    @Override
    public Optional<List<TagDto>> getTagDtoById(Long id) {
        TypedQuery<Tag> query = entityManager.
                createQuery("select t " +
                                "from Tag t join t.questions as tq where tq.id = :id",
                        Tag.class).
                setParameter("id", id);
        List<Tag> tagList = query.getResultList();
        List<TagDto> tagDtoList = tagList.stream()
                .map(tag -> modelMapper.map(tag, TagDto.class))
                .collect(Collectors.toList());
        return Optional.of(tagDtoList);
    }
}
