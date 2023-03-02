package com.javamentor.qa.platform.dao.impl.dto.user.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.user.pagination.UserPageByVoteDto;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

/**
 * Класс для возвращения PageDto<UserDto> отсортированных по количеству голосов в вопросах и ответах
 * @author Шапедько Андрей
 *  @since 02/03/2023
 */
@Repository
public class UserPageByVoteDtoImpl implements UserPageByVoteDto {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam;

        return entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.user.UserDto" +
                                "(u.id, u.email, u.fullName, u.city, u.imageLink, 0) " +
                                "FROM User u " +
                                "LEFT JOIN VoteQuestion vq1 " +
                                "WITH vq1.vote = :up AND vq1.user.id = u.id " +

                                "LEFT JOIN VoteQuestion vq2 " +
                                "WITH vq2.vote = :down AND vq2.user.id = u.id " +

                                "LEFT JOIN VoteAnswer va1 " +
                                "WITH va1.vote = :up AND va1.user.id = u.id " +

                                "LEFT JOIN  VoteAnswer va2 " +
                                "WITH va2.vote = :down AND va2.user.id = u.id " +
                                "GROUP BY u.id ORDER BY (count(vq1)-count(vq2)+count(va1)-count(va2)) DESC ",
                        UserDto.class)
                .setParameter("up", VoteType.UP_VOTE)
                .setParameter("down", VoteType.DOWN_VOTE)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    /**
     * @return возвращает общее число страниц
     */
    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(p) FROM User p ");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
