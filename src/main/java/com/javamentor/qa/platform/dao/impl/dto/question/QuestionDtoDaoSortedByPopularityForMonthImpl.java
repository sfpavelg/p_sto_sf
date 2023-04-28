package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDaoSortedByPopularityForMonth;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDtoDaoSortedByPopularityForMonthImpl implements QuestionDtoDaoSortedByPopularityForMonth {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

        return entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.question.QuestionDto ( " +
                                "q.id, " +
                                "q.title , " +
                                "q.user.id, " +
                                "(SELECT COALESCE(SUM(r.count),0) FROM Reputation r WHERE r.author.id = q.user.id), " +
                                "q.user.fullName, " +
                                "q.user.imageLink, " +
                                "q.description, " +
                                "(SELECT COUNT (qw.question.id) FROM QuestionViewed qw WHERE qw.localDateTime > cast(:monthAgo as timestamp) AND qw.question.id = q.id), " +
                                "(SELECT COUNT (a.question.id) FROM Answer a WHERE a.persistDateTime > cast(:monthAgo as timestamp) AND a.question.id = q.id), " +
                                "(SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.localDateTime > cast(:monthAgo as timestamp) AND vq.question.id = q.id AND vq.vote = 'up') - " +
                                "(SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.localDateTime > cast(:monthAgo as timestamp) AND vq.question.id = q.id AND vq.vote = 'down'), " +
                                "q.persistDateTime, " +
                                "q.lastUpdateDateTime) " +
                                "FROM Question q " +
                                "WHERE q.id IN (SELECT q.id FROM Question q JOIN q.tags AS tags WHERE :trackedTags IS NULL OR tags.id IN :trackedTags)" +
                                "AND q.id NOT IN (SELECT q.id FROM Question q JOIN q.tags AS tags WHERE tags.id IN :ignoredTags) " +
                                "ORDER BY (SELECT COUNT (qw.question.id) FROM QuestionViewed qw WHERE qw.localDateTime > cast(:monthAgo as timestamp) AND qw.question.id = q.id) + " +
                                "(SELECT COUNT (a.question.id) FROM Answer a WHERE a.persistDateTime > cast(:monthAgo as timestamp) AND a.question.id = q.id)*2 + " +
                                "((SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.localDateTime > cast(:monthAgo as timestamp) AND vq.question.id = q.id AND vq.vote = 'up') - " +
                                "(SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.localDateTime > cast(:monthAgo as timestamp) AND vq.question.id = q.id AND vq.vote = 'down'))*5 DESC",
                        QuestionDto.class)
                .setParameter("trackedTags", param.get("trackedTags"))
                .setParameter("ignoredTags", param.get("ignoredTags"))
                .setParameter("monthAgo", param.get("monthAgo"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();
    }
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager.createQuery(
                        "SELECT COUNT(q.id) " +
                                "FROM Question q " +
                                "WHERE q.id IN (SELECT q.id FROM Question q JOIN q.tags AS tags WHERE :trackedTags IS NULL OR tags.id IN :trackedTags) " +
                                "AND q.id NOT IN (SELECT q.id FROM Question q JOIN q.tags AS tags WHERE tags.id IN :ignoredTags)")
                .setParameter("trackedTags", param.get("trackedTags"))
                .setParameter("ignoredTags", param.get("ignoredTags"))
                .getSingleResult());
    }
}
