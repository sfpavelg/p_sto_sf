package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserProfileTagDtoDaoImpl implements UserProfileTagDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserProfileTagDto> getUserProfileTagDtoByUserId(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT id FROM question WHERE ");

//        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileTagDto(" +
//                        "t.name) " +
////"(SELECT COUNT )) " +
//                        "FROM Tag t " +
//                        "WHERE t.id = (SELECT q.id FROM question q WHERE user_id =: userId) " +
//                        "OR " +
//                        "(SELECT a.question_id FROM answer a WHERE user_id =: userId) ", UserProfileTagDto.class)
//                .setParameter("userId", userId );
        return null;
    }
}
