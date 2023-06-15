package com.javamentor.qa.platform.dao.impl.dto.answer;

import com.javamentor.qa.platform.dao.abstracts.dto.answer.AnswerDtoDao;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AnswerDtoDaoImpl implements AnswerDtoDao {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public List<AnswerDto> getAllByQuestionId(Long id) {

        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
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
    }

    @Override
    public Long getCountPerWeekByUserId(Long userId) {
        Query query = entityManager.createQuery("SELECT count(a.id) FROM Answer a " +
                "WHERE a.user.id = :userId " +
                "AND a.isDeleted = false " +
                "AND a.persistDateTime BETWEEN :weekAgo AND :today")
                .setParameter("userId", userId)
                .setParameter("weekAgo", LocalDateTime.now().minusWeeks(1))
                .setParameter("today", LocalDateTime.now());
        return (long) query.getSingleResult();
    }

    public Map<Long, List<AnswerDto>> getAnswersMapByQuestionId(List<Long> qId) {
        List<AnswerDto> answers = entityManager.createQuery(
                        "select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
                                "a.id, " +
                                "a.user.id, " +
                                "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = a.user.id), " +
                                "a.question.id, " +
                                "a.htmlBody, " +
                                "a.persistDateTime, " +
                                "a.isHelpful, " +
                                "a.dateAcceptTime, " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'UP_VOTE') - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'DOWN_VOTE')," +
                                "a.user.imageLink, " +
                                "a.user.nickname) " +
                                "from Answer a " +
                                "JOIN a.question q where a.question.id IN :id and a.isDeleted = false " +
                                "ORDER BY " +
                                "a.isHelpful DESC," +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'UP_VOTE') - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'DOWN_VOTE') DESC"
                        , AnswerDto.class)
                .setParameter("id", qId)
                .getResultList();
        Map<Long, List<AnswerDto>> answersMap = new HashMap<>();

        answers.forEach(answerList -> answersMap.computeIfAbsent(answerList.getQuestionId(), key -> new ArrayList<>())
                .add(new AnswerDto(
                        answerList.getId(),
                        answerList.getUserId(),
                        answerList.getUserReputation(),
                        answerList.getQuestionId(),
                        answerList.getBody(),
                        answerList.getPersistDate(),
                        answerList.getIsHelpful(),
                        answerList.getDateAccept(),
                        answerList.getCountValuable(),
                        answerList.getImage(),
                        answerList.getNickName()
                )));
        return answersMap;
    }

    public List<AnswerDto> getAllByQuestionIdSortedByUsefulAndCount(Long id) {

        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
                                "a.id, " +
                                "a.user.id, " +
                                "(select coalesce(sum(rep.count), 0) from Reputation as rep where rep.author.id = a.user.id), " +
                                "a.question.id, " +
                                "a.htmlBody, " +
                                "a.persistDateTime, " +
                                "a.isHelpful, " +
                                "a.dateAcceptTime, " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'UP_VOTE') - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'DOWN_VOTE')," +
                                "a.user.imageLink, " +
                                "a.user.nickname) " +
                                "from Answer a " +
                                "JOIN a.question q where a.question.id IN :id and a.isDeleted = false " +
                                "ORDER BY " +
                                "a.isHelpful DESC," +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'UP_VOTE') - " +
                                "(SELECT COUNT(va.answer.id) FROM VoteAnswer va WHERE va.answer.id = a.id AND va.vote = 'DOWN_VOTE') DESC"
                        , AnswerDto.class)
                .setParameter("id", id).getResultList();
    }

    @Override
    public List<AnswerDto> getAllDeletedAnswersByUserId(Long userId) {
        return entityManager.createQuery("select new com.javamentor.qa.platform.models.dto.answer.AnswerDto(" +
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
                        "from Answer a where a.user.id = :userId and a.isDeleted = true", AnswerDto.class)
                .setParameter("userId", userId).getResultList();
    }
}
