package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.CommentDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Repository
public class CommentDtoDaoImpl implements CommentDtoDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionCommentDto> getAllCommentDtoByQuestionId(Long questionId) {
        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.question.QuestionCommentDto(" +
                        "cq.comment.id, " +
                        "cq.question.id, " +
                        "cq.comment.lastUpdateDateTime, " +
                        "cq.comment.persistDateTime, " +
                        "cq.comment.text, " +
                        "cq.comment.user.id, " +
                        "cq.comment.user.imageLink, " +
                        "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = cq.comment.user.id)) " +
                        "FROM  CommentQuestion cq " +
                        "WHERE cq.question.id = :questionId", QuestionCommentDto.class)
                .setParameter("questionId", questionId).getResultList();
    }

    @Override
    public Optional<QuestionCommentDto> getCommentById(Long id) {
        Query query =  entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.question.QuestionCommentDto(" +
                        "cq.comment.id, " +
                        "cq.question.id, " +
                        "cq.comment.lastUpdateDateTime, " +
                        "cq.comment.persistDateTime, " +
                        "cq.comment.text, " +
                        "cq.comment.user.id, " +
                        "cq.comment.user.imageLink, " +
                        "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = cq.comment.user.id)) " +
                        "FROM  CommentQuestion cq " +
                        "WHERE cq.comment.id = :id", QuestionCommentDto.class)
                .setParameter("id", id);
        return SingleResultUtil.getSingleResultOrNull(query);
    }
}
