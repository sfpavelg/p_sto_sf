package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.question.QuestionDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.Optional;

@Repository
public class QuestionDtoDaoImpl implements QuestionDtoDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id) {
        Query query = entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.question.QuestionDto ( q.id, " +
                                "q.title , " +
                                "u.id, " +
                                "coalesce(sum(r.count),0), " +
                                "u.fullName, " +
                                "u.imageLink, " +
                                "q.description , " +
                                "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id), " +
                                "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = :id and vq.vote = 'up') - " +
                                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = :id and vq.vote = 'down'), " +
                                "q.persistDateTime, " +
                                "q.lastUpdateDateTime) " +

                                "from Question q " +
                                "LEFT JOIN User u ON u.id = q.user.id " +
                                "LEFT JOIN Reputation r ON u.id = r.author.id " +

                                "where q.id = :id group by q.id, u.id", QuestionDto.class)
                .setParameter("id", id);

        return SingleResultUtil.getSingleResultOrNull(query);
    }


}
