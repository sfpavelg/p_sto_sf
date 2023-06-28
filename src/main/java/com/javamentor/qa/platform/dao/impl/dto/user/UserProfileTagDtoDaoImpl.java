package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class UserProfileTagDtoDaoImpl implements UserProfileTagDtoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserProfileTagDto> getUserProfileTagDtoWithoutVotesByUserId(Long userId) {
        List<Tuple> userProfileDtoList = entityManager.createNativeQuery("SELECT tag.name as tagName, COUNT(tag_id) AS countAnswerQuestion " +
                        "FROM question_has_tag" +
                        "         JOIN tag " +
                        "              ON question_has_tag.tag_id = tag.id " +
                        "WHERE question_id IN (" +
                        "    SELECT id FROM question WHERE user_id = :userId " +
                        "    UNION " +
                        "    SELECT question_id FROM answer WHERE user_id = :userId " +
                        ")" +
                        "GROUP BY tag.name;", Tuple.class).setParameter("userId", userId)
                .getResultList();

        List<UserProfileTagDto> tags = new ArrayList<>();
        for (Tuple tuple : userProfileDtoList) {
            tags.add( new UserProfileTagDto(tuple.get("tagname").toString(), Long.valueOf(tuple.get("countanswerquestion").toString())));
        }

            return tags;
    }

    public Map<String, Long> getTagVotesFromQuestionsByList (List<String> tagNames) {
        List<Tuple> questionTagVotes = entityManager.createQuery("SELECT t.name as tagName " +
                ", sum (case when vq.vote ='UP_VOTE' then 1 else -1 end ) as votes " +
                "FROM Tag t join  t.questions q " +
                "JOIN VoteQuestion vq ON vq.question.id = q.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name", Tuple.class).setParameter("tagList", tagNames).getResultList();

        Map<String, Long> resultMap = questionTagVotes.stream().collect(Collectors.toMap(
                tuple -> ((String) tuple.get("tagName")),
                tuple -> ((Number) tuple.get("votes")).longValue()
        ));

        return resultMap;
    }

    public Map<String, Long> getTagVotesFromAnswersByList (List<String> tagNames) {
        List<Tuple> answerTagVotes = entityManager.createQuery("SELECT  t.name as tagName " +
                ", SUM (CASE WHEN va.vote ='UP_VOTE' THEN 1 ELSE -1 END ) as votes " +
                "FROM Tag t JOIN  t.questions q " +
                "JOIN Answer a ON a.question.id = q.id " +
                "JOIN VoteAnswer va ON va.answer.id = a.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name", Tuple.class).setParameter("tagList", tagNames).getResultList();

        Map<String, Long> resultMap = answerTagVotes.stream().collect(Collectors.toMap(
                tuple -> ((String) tuple.get("tagName")),
                tuple -> ((Number) tuple.get("votes")).longValue()
        ));

        return resultMap;
    }
}




