package com.javamentor.qa.platform.dao.impl.dto.question;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.VoteQuestionDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.question.VoteQuestionDto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Optional;

public class VoteQuestionDtoDaoImpl implements VoteQuestionDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VoteQuestionDto> getVoteByQuestionIdAndUserId(Long questionId, Long userId) {
        Query query = entityManager.createQuery(
                "select new com.javamentor.qa.platform.models.dto.VoteQuestionDto(v.id, v.user.id, v.question.id, v.lovalDateTime, v.vote)" +
                        "from VoteQuestion v" +
                        "where v.user.id =: userId and v.qustion.id =: questionId", VoteQuestionDto.class)

                .setParameter("questionId", questionId)
                .setParameter("userId", userId);


        return SingleResultUtil.getSingleResultOrNull(query);
    }
}
