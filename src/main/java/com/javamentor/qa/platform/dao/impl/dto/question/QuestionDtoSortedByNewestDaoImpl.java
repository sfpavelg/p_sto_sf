package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoSortedByNewestDao;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDtoSortedByNewestDaoImpl implements QuestionDtoSortedByNewestDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionViewDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        return entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.question.QuestionViewDto( " +
                                "q.id, q.title , q.user.id, " +
                                "(SELECT coalesce(sum(rep.count),0) FROM Reputation rep WHERE rep.author.id = q.user.id), " +
                                "q.user.fullName, q.user.imageLink, q.description , " +
                                "(SELECT count(qw.question.id) FROM QuestionViewed qw WHERE qw.question.id = q.id), " +
                                "0L , " +
                                "(SELECT count(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'up') - " +
                                "(SELECT count(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'down'), " +
                                "q.persistDateTime, q.lastUpdateDateTime ) " +
                                "FROM Question q " +
                                "WHERE  q.id in (select q.id from Question q join q.tags as tags where :trackedTag is null or tags.id in :trackedTag) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTag) " +
                                "ORDER BY q.persistDateTime DESC",
                        QuestionViewDto.class)
                .setParameter("trackedTag", param.get("trackedTag"))
                .setParameter("ignoredTag", param.get("ignoredTag"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager.createQuery(
                        "select count(q.id) " +
                                "from Question q " +
                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTag is null or tags.id in :trackedTag) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTag)")
                .setParameter("trackedTag", param.get("trackedTag"))
                .setParameter("ignoredTag", param.get("ignoredTag"))
                .getSingleResult());
    }
}
