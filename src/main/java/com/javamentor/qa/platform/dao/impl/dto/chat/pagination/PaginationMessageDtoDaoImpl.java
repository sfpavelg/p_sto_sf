package com.javamentor.qa.platform.dao.impl.dto.chat.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.chat.pagination.PaginationMessageDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.chat.MessageDto;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class PaginationMessageDtoDaoImpl implements PaginationMessageDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<MessageDto> getItems(Map<String, Object> param) {
//        int itemsOnPageParam = (int) param.get("itemsOnPage");
//        int itemPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.chat.MessageDto(" +
                "m.id," +
                "m.message," +
                "m.userSender.nickname," +
                "m.userSender.id," +
                "m.userSender.imageLink," +
                "m.persistDate)" +
                "FROM Message m " +
                "WHERE m.userSender.id = :userId", MessageDto.class).setParameter("userId", param.get("userId"));
        return (List<MessageDto>) query.getResultList();

    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long)  entityManager
                .createQuery("SELECT COUNT(m.userSender.id) FROM Message m WHERE m.userSender.id = :userId")
                .setParameter("userId", param.get("userId")).getSingleResult());
    }

//    @Override
//    public List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId) {
//        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.question.QuestionCommentDto(" +
//                        "cq.comment.id, " +
//                        "cq.question.id, " +
//                        "cq.comment.lastUpdateDateTime, " +
//                        "cq.comment.persistDateTime, " +
//                        "cq.comment.text, " +
//                        "cq.comment.user.id, " +
//                        "cq.comment.user.imageLink, " +
//                        "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = cq.comment.user.id)) " +
//                        "FROM  CommentQuestion cq " +
//                        "WHERE cq.question.id = :questionId", QuestionCommentDto.class)
//                .setParameter("questionId", questionId).getResultList();
//    }
//
//    @Override
//    public Optional<QuestionCommentDto> getCommentById(Long id) {
//        Query query =  entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.question.QuestionCommentDto(" +
//                        "cq.comment.id, " +
//                        "cq.question.id, " +
//                        "cq.comment.lastUpdateDateTime, " +
//                        "cq.comment.persistDateTime, " +
//                        "cq.comment.text, " +
//                        "cq.comment.user.id, " +
//                        "cq.comment.user.imageLink, " +
//                        "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = cq.comment.user.id)) " +
//                        "FROM  CommentQuestion cq " +
//                        "WHERE cq.comment.id = :id", QuestionCommentDto.class)
//                .setParameter("id", id);
//        return SingleResultUtil.getSingleResultOrNull(query);
//    }
//    @Override
//    public List<QuestionViewDto> getItems(Map<String, Object> param) {
//        int itemsOnPageParam = (int) param.get("itemsOnPage");
//        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
//
//        return entityManager.createQuery(
//                        "select new com.javamentor.qa.platform.models.dto.question.QuestionViewDto ( q.id, " +
//                                "q.title , " +
//                                "q.user.id, " +
//                                "(select coalesce(sum(r.count),0) from Reputation r where r.author.id = q.user.id), " +
//                                "q.user.fullName, " +
//                                "q.user.imageLink, " +
//                                "q.description, " +
//                                "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id), " +
//                                "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
//                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'up') - " +
//                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'down'), " +
//                                "q.persistDateTime, " +
//                                "q.lastUpdateDateTime) " +
//
//                                "from Question q " +
//                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTags is null or tags.id in :trackedTags ) " +
//                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTags) " +
//                                "order by q.id", QuestionViewDto.class)
//                .setParameter("trackedTags", param.get("trackedTags"))
//                .setParameter("ignoredTags", param.get("ignoredTags"))
//                .setMaxResults(itemsOnPageParam)
//                .setFirstResult(itemsPositionParam)
//                .getResultList();
//    }
//
//    @Override
//    public int getTotalResultCount(Map<String, Object> param) {
//        return Math.toIntExact((Long) entityManager.createQuery(
//                        "select count(q.id) " +
//                                "from Question q " +
//                                "where q.id in (select q.id from Question q join q.tags as tags where :trackedTags is null or tags.id in :trackedTags) " +
//                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTags)")
//                .setParameter("trackedTags", param.get("trackedTags"))
//                .setParameter("ignoredTags", param.get("ignoredTags"))
//                .getSingleResult());
//    }
}
