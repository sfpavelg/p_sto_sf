package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
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

    @Override
    public List<UserProfileQuestionDto> getAllUserRemovedQuestion(Long id) {
        return entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileQuestionDto(" + "q.id, " + "q.title, " + "size(q.answers), "
                + "q.persistDateTime) " + "FROM Question q where q.user.id =:id AND q.isDeleted = true", UserProfileQuestionDto.class).setParameter("id", id).getResultList();
    }

    @Override
    public List<UserDto> getPageWithListTop10UsersAnswers() {

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime weekBefore = now.minusDays(7L);

        Query query = entityManager.createQuery
                ("SELECT new com.javamentor.qa.platform.models.dto.user.UserDto (" +
                        "u.id, " +
                        "u.email, " +
                        "u.fullName, " +
                        "u.city, " +
                        "u.imageLink, " +
                        "cast(((select sum(r.count) from Reputation r where r.author.id = u.id)) as integer )) " +
                        "from User u, Answer a " +
                        "where a.user.id = u.id and a.persistDateTime between :weekBefore and :now " +
                        "group by u.id " +
                        "order by count(a.user.id) desc, " +
                        "(((select count(va.answer.id) from VoteAnswer va, Answer a where va.answer.id = a.id and a.user.id = u.id and va.vote = 'UP_VOTE'))  -   " +
                        "((select count(va.answer.id) from VoteAnswer va, Answer a where va.answer.id = a.id and a.user.id = u.id and va.vote = 'DOWN_VOTE'))) desc, " +
                        "cast(((select sum(r.count) from Reputation r where r.author.id = u.id)) as integer ) desc", UserDto.class);

        return (List<UserDto>) query
                .setParameter("weekBefore", weekBefore)
                .setParameter("now", now)
                .setMaxResults(10).getResultList();
    }
}
