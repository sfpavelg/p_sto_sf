package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileQuestionDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserProfileQuestionDtoDaoImpl implements UserProfileQuestionDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId) {
        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto(" +
                "q.id, " +
                "q.title, " +
                "size(q.answers), " +
                "q.persistDateTime) " +
                "FROM Question q where q.user.id = :userId").setParameter("userId", userId);
        return query.getResultList();
    }
}