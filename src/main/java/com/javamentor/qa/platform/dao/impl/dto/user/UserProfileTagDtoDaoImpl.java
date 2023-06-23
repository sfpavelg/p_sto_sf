package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class UserProfileTagDtoDaoImpl implements UserProfileTagDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public Map<String, Long> getUserProfileTagDtoWithoutVotesByUserId(Long userId) {
        List<Tuple> userProfileDtoList = entityManager.createNativeQuery("SELECT tag.name as tagName, COUNT(tag_id) AS countVoteTag " +
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

        Map<String, Long> tags = userProfileDtoList.stream().collect(Collectors.toMap(
                tuple -> ((String) tuple.get("tagname")),
                tuple -> ((Number) tuple.get("countvotetag")).longValue()
        ));

        return tags;
    }


    public Map<String, Long> getTagVotesByList(Map<String, Long> tags) {
        List<Tuple> userProfileDtoList1 = entityManager.createQuery("SELECT t.name as tagName " +
                ", sum (case when vq.vote ='UP_VOTE' then 1 else -1 end ) as votes " +
                "FROM Tag t join  t.questions q " +
                "JOIN VoteQuestion vq ON vq.question.id = q.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name", Tuple.class).setParameter("tagList", tags.keySet()).getResultList();


        List<Tuple> userProfileDtoList2 = entityManager.createQuery("SELECT  t.name as tagName " +
                ", SUM (CASE WHEN va.vote ='UP_VOTE' THEN 1 ELSE -1 END ) as votes " +
                "FROM Tag t JOIN  t.questions q " +
                "JOIN Answer a ON a.question.id = q.id " +
                "JOIN VoteAnswer va ON va.answer.id = a.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name", Tuple.class).setParameter("tagList", tags.keySet()).getResultList();


        Map<String, Long> temp = userProfileDtoList2.stream().collect(Collectors.toMap(
                tuple -> ((String) tuple.get("tagName")),
                tuple -> ((Number) tuple.get("votes")).longValue()
        ));

        Map<String, Long> resultMap = userProfileDtoList1.stream().collect(Collectors.toMap(
                tuple -> ((String) tuple.get("tagName")),
                tuple -> ((Number) tuple.get("votes")).longValue()
        ));

        for (Map.Entry<String, Long> entry : temp.entrySet()) {
            resultMap.put(entry.getKey(), Long.sum(resultMap.containsKey(entry.getKey()) ? resultMap.get(entry.getKey()) : 0, entry.getValue()));
        }

        return resultMap;
    }
}


