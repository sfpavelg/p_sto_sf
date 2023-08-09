package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileReputationDtoDao;
import com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class UserProfileReputationDtoDaoImpl implements UserProfileReputationDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<UserProfileReputationDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

        return entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.user.reputation.UserProfileReputationDto" +
                                "(rep.count, " +
                                "rep.persistDate, " +
                                "voteA.vote, " +
                                "rep.type) " +

                                "from Reputation rep " +
                                "left join Answer a on rep.answer.id = a.id " +
                                "left join VoteAnswer voteA on a.id = voteA.answer.id " +
                                "where rep.author.id = :userId order by rep.persistDate desc",
                        UserProfileReputationDto.class)
                .setParameter("userId", param.get("userId"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return ((Long) (entityManager.createQuery(
                "SELECT COUNT(r) " +
                        "FROM Reputation r " +
                        "LEFT JOIN Answer a on r.answer.id = a.id " +
                        "LEFT JOIN VoteAnswer v on a.id = v.answer.id " +
                        "WHERE r.author.id = :userId")
                .setParameter("userId", param.get("userId"))
                .getSingleResult())).intValue();
    }
}
