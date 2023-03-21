package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionCommentDtoDao;
import com.javamentor.qa.platform.models.dto.question.QuestionCommentDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class QuestionCommentDtoDaoImpl implements QuestionCommentDtoDao {
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
}
