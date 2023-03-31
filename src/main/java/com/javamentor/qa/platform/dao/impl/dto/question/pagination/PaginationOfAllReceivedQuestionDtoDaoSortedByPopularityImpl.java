package com.javamentor.qa.platform.dao.impl.dto.question.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.PaginationOfAllReceivedQuestionDtoDaoSortedByPopularity;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class PaginationOfAllReceivedQuestionDtoDaoSortedByPopularityImpl implements PaginationOfAllReceivedQuestionDtoDaoSortedByPopularity {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

        return entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.question.QuestionDto ( q.id, " +
                                "q.title , " +
                                "q.user.id, " +
                                "(select coalesce(sum(r.count),0) from Reputation r where r.author.id = q.user.id), " +
                                "q.user.fullName, " +
                                "q.user.imageLink, " +
                                "q.description, " +
                                "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id), " +
                                "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'up') - " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'down'), " +
                                "q.persistDateTime, " +
                                "q.lastUpdateDateTime) " +

                                "from Question q " +
                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTags is null or tags.id in :trackedTags ) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTags) " +
                                "order by q.id", QuestionDto.class)
                .setParameter("trackedTags", param.get("trackedTags"))
                .setParameter("ignoredTags", param.get("ignoredTags"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager.createQuery(
                        "select count(q.id) " +
                                "from Question q " +
                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTags is null or tags.id in :trackedTags) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTags)")
                .setParameter("trackedTags", param.get("trackedTags"))
                .setParameter("ignoredTags", param.get("ignoredTags"))
                .getSingleResult());
    }
}
