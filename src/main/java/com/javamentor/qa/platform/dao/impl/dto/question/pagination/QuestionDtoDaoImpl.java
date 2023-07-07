package com.javamentor.qa.platform.dao.impl.dto.question.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.ParametersForSearchQuestionDto;
import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.QueryForSearchQuestionDto;
import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;
import java.util.Optional;

@Repository
public class QuestionDtoDaoImpl implements QuestionDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    private final QueryForSearchQuestionDto queryForSearchQuestionDto;

    public QuestionDtoDaoImpl(QueryForSearchQuestionDto queryForSearchQuestionDto) {
        this.queryForSearchQuestionDto = queryForSearchQuestionDto;
    }

    @Override
    public Optional<QuestionDto> getQuestionDtoById(Long id, Long userId) {
        Query query = entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.question.QuestionDto ( " +
                                "q.id, " +
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
                                "q.lastUpdateDateTime, " +
                                "(select vq.vote from VoteQuestion vq where vq.user.id = :userId and vq.question.id = q.id)) " +

                                "from Question q " +
                                "LEFT JOIN User u ON u.id = q.user.id " +
                                "LEFT JOIN Reputation r ON u.id = r.author.id " +

                                "where q.id = :id" +
                                " group by q.id, u.id", QuestionDto.class)
                .setParameter("id", id)
                .setParameter("userId", userId);

        return SingleResultUtil.getSingleResultOrNull(query);
    }

    @Override
    public Long getCountQuestionDto() {
        Query query = entityManager.createQuery("select count(*) from Question");
        return (long) query.getSingleResult();
    }

    @Override
    public List<QuestionDto> getQuestionDtoByUserIdAndValue(Long userId, String value) {
        String stringQuery = ("select distinct new com.javamentor.qa.platform.models.dto.question.QuestionDto (" +
                "q.id, " +
                "q.title, " +
                "u.id, " +
                "(select distinct coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = u.id), " +
                "u.fullName, " +
                "u.imageLink, " +
                "q.description, " +
                "(select distinct count (qw.question.id) from QuestionViewed qw join qw.question as qwq join qw.user where qwq.id = q.id), " +
                "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
                "(select distinct count(vq.question.id) from VoteQuestion vq join vq.user join vq.question as vqq where vqq.id = q.id and vq.vote = 'up')" +
                " - (select distinct count(vq.question.id) from VoteQuestion vq join vq.user join vq.question as vqq where vqq.id = q.id and vq.vote = 'down'), " +
                "q.persistDateTime, " +
                "q.lastUpdateDateTime, " +
                "(select distinct vq.vote from VoteQuestion vq join vq.user join vq.question as vqq where vq.user.id = :userId and vqq.id = q.id)) " +

                "from Question q " +
                "join q.user as u " +
                "join q.tags as t " +
                "where (u.id = :user or :user is null)");
        Query query = entityManager.createQuery(queryForSearchQuestionDto.editQueryByStringQueryAndParameters(stringQuery, value));
        query.setParameter("userId", userId);
        query = queryForSearchQuestionDto.setAllParametersForQueryByQueryAndValue(query, value);


        return query.getResultList();
    }
}