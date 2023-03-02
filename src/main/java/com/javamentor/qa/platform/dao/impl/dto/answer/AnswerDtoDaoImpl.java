package com.javamentor.qa.platform.dao.impl.dto.answer;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class AnswerDtoDaoImpl implements AnswerDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AnswerDto> getAllByQuestionId(Long id) {
        List<AnswerDto> answerDtoList = entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
                        "a.id, " +
                        "a.user.id, " +
                        "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = a.user.id), " +
                        "a.question.id, " +
                        "a.htmlBody, " +
                        "a.persistDateTime, " +
                        "a.isHelpful, " +
                        "a.dateAcceptTime, " +
                        "(select coalesce(sum(case when (va.vote = 'UP') then 1 else -1 end), 0) from VoteAnswer as va where va.answer.id = a.id), " +
                        "a.user.imageLink, " +
                        "a.user.nickname) " +
                        "from Answer a where a.question.id = :id and a.isDeleted = false", AnswerDto.class)
                .setParameter("id", id).getResultList();

        return answerDtoList;
    }
}
