package com.javamentor.qa.platform.dao.impl.dto.answer.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.pagination.AnswerPageByVoteDto;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.VoteType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
/**
 * Class for returning PageDto<QuestionDto> sorted by votes
 */
@Repository
public class AnswerPageByVoteDtoImpl implements AnswerPageByVoteDto {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AnswerDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

        return entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.answer.AnswerDto" +
                                "(a.id, " +
                                "a.user.id, " +
                                "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = a.user.id), " +
                                "a.question.id, " +
                                "a.htmlBody, " +
                                "a.persistDateTime, " +
                                "a.isHelpful, " +
                                "a.dateAcceptTime, " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = :up) - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = :down)," +
                                "a.user.imageLink, " +
                                "a.user.nickname) " +

                                "from Answer a order by " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = :up) - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = :down) DESC, a.id",
                        AnswerDto.class)
                .setParameter("up", VoteType.UP_VOTE)
                .setParameter("down", VoteType.DOWN_VOTE)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        Query query = entityManager.createQuery("SELECT COUNT(a) FROM Answer a");
        return Integer.parseInt(query.getSingleResult().toString());
    }
}
