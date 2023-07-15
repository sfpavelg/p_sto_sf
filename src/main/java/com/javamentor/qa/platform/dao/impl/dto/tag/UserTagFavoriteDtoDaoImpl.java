package com.javamentor.qa.platform.dao.impl.dto.tag;

import com.javamentor.qa.platform.dao.abstracts.dto.tag.UserTagFavoriteDtoDao;
import com.javamentor.qa.platform.models.dto.tag.UserTagFavoriteDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
@Repository
public class UserTagFavoriteDtoDaoImpl implements UserTagFavoriteDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserTagFavoriteDto> getByUserId(Long userId) {
        List<Tuple> userFavoriteDtoList = entityManager.createNativeQuery("SELECT tag.id as id, tag.name as tagName, COUNT(tag_id) AS countMessage " +
                        "FROM question_has_tag" +
                        "         JOIN tag " +
                        "              ON question_has_tag.tag_id = tag.id " +
                        "WHERE question_id IN (" +
                        "    SELECT id FROM question WHERE user_id = :userId " +
                        "    UNION " +
                        "    SELECT question_id FROM answer WHERE user_id = :userId " +
                        ")" +
                        "GROUP BY tag.id ORDER BY countMessage DESC;", Tuple.class).setParameter("userId", userId)
                .getResultList();

        List<UserTagFavoriteDto> tags = new ArrayList<>();
        for (Tuple tuple : userFavoriteDtoList) {
            tags.add(new UserTagFavoriteDto(Long.valueOf(tuple.get("id").toString()), tuple.get("tagname").toString(), Long.valueOf(tuple.get("countmessage").toString())));
        }

        return tags;
    }
}


