package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileCommentDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileCommentDto;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class UserProfileCommentDtoDaoImpl implements UserProfileCommentDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserProfileCommentDto> getItems(Map<String, Object> param) {

        Long userId = (Long) param.get("userId");
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

        Query query = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.user.UserProfileCommentDto(" +
                "c.id, " +
                "c.text, " +
                "c.persistDateTime, " +
                "case when c.commentType=0 then a.question.id else cq.question.id end, " +
                "ca.answer.id, " +
                "c.commentType) " +
                "from Comment c " +
                "left join CommentAnswer ca " +
                "on c.id=ca.comment.id " +
                "left join Answer a " +
                "on ca.answer.id = a.id " +
                "left join CommentQuestion cq " +
                "on c.id = cq.comment.id " +
                "where c.user.id = :id", UserProfileCommentDto.class)
                .setParameter("id", userId)
                .setFirstResult(itemsPositionParam)
                .setMaxResults(itemsOnPageParam);
        return (List<UserProfileCommentDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager
                .createQuery("select count(c.id) from Comment c where c.user.id = :userId")
                .setParameter("userId", param.get("userId")).getSingleResult());
    }
}
