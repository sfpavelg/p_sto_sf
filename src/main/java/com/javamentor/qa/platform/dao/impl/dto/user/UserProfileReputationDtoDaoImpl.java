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
                                "CASE WHEN rep.type = 0 THEN 2 " +
                                "WHEN rep.type = 3 THEN voteQuestion.vote " +
                                "WHEN rep.type = 4 THEN voteAnswer.vote END) " +

                                "from Reputation rep join VoteAnswer voteAnswer on rep.id = voteAnswer.answer.id " +
                                "join VoteQuestion voteQuestion on rep.id = voteQuestion.question.id " +
                                "where rep.author.id = :userId and (rep.type = 0 or rep.type = 3 or rep.type = 4) order by rep.persistDate desc",
                        UserProfileReputationDto.class)
                .setParameter("userId", param.get("userId"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return entityManager.createQuery("SELECT COUNT(r) FROM Reputation r", Integer.class).getSingleResult();
    }
}
