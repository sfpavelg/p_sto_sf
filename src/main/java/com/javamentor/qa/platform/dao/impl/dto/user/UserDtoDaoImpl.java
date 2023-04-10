package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDtoDaoImpl implements UserDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<UserDto> getById(Long id) {

        return SingleResultUtil.getSingleResultOrNull(
                entityManager.createQuery(
                                "SELECT new com.javamentor.qa.platform.models.dto.user.UserDto" +
                                        "(u.id, u.email, u.fullName, u.city, u.imageLink, " +
                                        "cast(coalesce(sum(r.count), 0) as integer )) " +
                                        "from User u left join Reputation r " +
                                        "with r.author.id = u.id where u.id = :id group by u.id",
                                UserDto.class)
                        .setParameter("id", id));
    }

    @Override
    public List<UserProfileQuestionDto> getAllUserProfileQuestionDtoByUserId(Long userId) {
        return entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto(" +
                        "q.id, " +
                        "q.title, " +
                        "size(q.answers), " +
                        "q.persistDateTime) " +
                        "FROM Question q where q.user.id = :userId", UserProfileQuestionDto.class).setParameter("userId", userId)
                .getResultList();
    }
}
