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
                        "SELECT new com.javamentor.qa.platform.models.dto.question.QuestionDto ( " +
                                "q.id, " +
                                "q.title , " +
                                "q.user.id, " +
                                "(select coalesce(sum(r.count),0) from Reputation r where r.author.id = q.user.id), " +
                                "q.user.fullName, " +
                                "q.user.imageLink, " +
                                "q.description, " +
                                "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id) as views_count, " +
                                "(select count (a.question.id) from Answer a where a.question.id = q.id) as answers_count, " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'up') - " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'down'), " +
                                "q.persistDateTime, " +
                                "q.lastUpdateDateTime) " +

                                "from Question q " +
                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTags is null or tags.id in :trackedTags ) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTags) " +
        //                        "order by q.id",
                       //         "order by views_count + answers_count ",
                                "ORDER BY (select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id) + " +
                                "(select count (a.question.id) from Answer a where a.question.id = q.id)*2 + " +
                                "((select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'up') - " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'down'))*5 DESC",
                        QuestionDto.class)

//                        "SELECT new com.javamentor.qa.platform.models.dto.question.QuestionDto ( " +
//                                "q.id, " +
//                                "q.title, " +
//                                "q.user.id, " +
//                                "COALESCE(SUM(r.count), 0), " +
//                                "q.user.fullName, " +
//                                "q.user.imageLink, " +
//                                "q.description, " +
//                                "COUNT(DISTINCT qw.id) as views_count, " +
//                                "COUNT(DISTINCT a.id) as answers_count, " +
//                                "SUM(CASE WHEN vq.vote = 'up' THEN 1 WHEN vq.vote = 'down' THEN -1 ELSE 0 END) as rating_sum, " +
//                                "q.persistDateTime, " +
//                                "q.lastUpdateDateTime) " +
//                                "FROM Question q " +
//                                "LEFT JOIN Reputation r ON r.author.id = q.user.id " +
//                                "LEFT JOIN QuestionViewed qw ON qw.question.id = q.id " +
//                                "LEFT JOIN Answer a ON a.question.id = q.id " +
//                                "LEFT JOIN VoteQuestion vq ON vq.question.id = q.id " +
//                                "LEFT JOIN q.tags as tags ON tags.id IN :trackedTags OR 1=1 " +
////                               OR 1=1, чтобы учитывать все вопросы, если trackedTags is null
//                                "WHERE NOT EXISTS ( " +
//                                "SELECT 1 FROM Question q2 JOIN q2.tags as tags2 WHERE tags2.id IN :ignoredTags and q2.id=q.id) " +
//                                "GROUP BY q.id, q.title, q.user.id, q.user.fullName, q.user.imageLink, q.description, q.persistDateTime, q.lastUpdateDateTime " +
////                                "ORDER BY views_count + answers_count + rating_sum DESC ",
//                                "ORDER BY COUNT(DISTINCT qw.id) + COUNT(DISTINCT a.id) + SUM(CASE WHEN vq.vote = 'up' THEN 1 WHEN vq.vote = 'down' THEN -1 ELSE 0 END) DESC",
////                                "ORDER BY q.id",
//                        QuestionDto.class)
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
