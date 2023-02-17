package com.javamentor.qa.platform.dao.impl.dto.answer;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.dao.util.SingleResultUtil;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.hibernate.transform.ResultTransformer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class AnswerDtoDaoImpl implements AnswerDtoDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<AnswerDto> getAllByQuestionId(Long id) {
        final String query = "select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
                        "a.id, " +
                        "a.user.id, " +
                        "(select sum(rep.count) from Reputation as rep where rep.author.id = a.user.id), " +
                        "a.question.id, " +
                        "a.htmlBody, " +
                        "a.persistDateTime, " +
                        "a.isHelpful, " +
                        "a.dateAcceptTime, " +
                        "(select sum(case va.vote when 'UP_VOTE' then 1 else -1 end) from VoteAnswer as va where va.answer.id = a.id), " +
                        "(select u.imageLink from User as u where u.id = a.user.id), " +
                        "(select u.nickname from User as u where u.id = a.user.id) " +
                        "from Answer a where a.question.id = :id and a.isDeleted = false";

        return (List<AnswerDto>) entityManager.createQuery(query)
                .setParameter("id", id)
                .unwrap(org.hibernate.query.Query.class)
                .setResultTransformer(
                        new ResultTransformer() {
                            @Override
                            public Object transformTuple(Object[] objects, String[] strings) {
                                return new AnswerDto(
                                     ((Long) objects[0]).longValue(),
                                     ((Long) objects[1]).longValue(),
                                     Optional.ofNullable(objects[2]).map(o2 -> ((Long) o2).longValue()).orElse(0L),
                                     ((Long) objects[3]).longValue(),
                                     ((String) objects[4]),
                                     ((LocalDateTime) objects[5]),
                                     ((Boolean) objects[6]).booleanValue(),
                                     ((LocalDateTime) objects[7]),
                                     Optional.ofNullable(objects[8]).map(o8 -> ((Long) o8).longValue()).orElse(0L),
                                     ((String) objects[9]),
                                     ((String) objects[10])
                                );
                            }

                            @Override
                            public List transformList(List list) {
                                return list;
                            }
                        }
                )
                .getResultList();
    }
}
