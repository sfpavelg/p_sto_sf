package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;


@Repository
public class UserProfileTagDtoDaoImpl implements UserProfileTagDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserProfileTagDto> getUserProfileTagDtoWithoutVotesByUserId(Long userId) {
        Query query = entityManager.createNativeQuery("SELECT tag.name as tagName, COUNT(tag_id) AS countVoteTag " +
                "FROM question_has_tag" +
                "         JOIN tag " +
                "              ON question_has_tag.tag_id = tag.id " +
                "WHERE question_id IN (" +
                "    SELECT id FROM question WHERE user_id = ? " +
                "    UNION " +
                "    SELECT question_id FROM answer WHERE user_id = ?" +
                ")" +
                "GROUP BY tag.name;").setParameter(1, userId).setParameter(2, userId);
        List<Object[]> lst = (List<Object[]>) query.getResultList();
        List<UserProfileTagDto> result = new ArrayList<>();
        for (int i = 0; i < lst.size(); i++) {
            Object[] o = lst.get(i);
            result.add(new UserProfileTagDto(o[0].toString(), Long.valueOf(o[1].toString())));
        }
        return result;
    }


    public List<Object[]> getTagVotesByList(List<String> tagList) {
//        конструктор нельзя использовать потому что уже есть String, Long constructor.
        Query questionVotes = entityManager.createQuery("SELECT t.name" +
                ", sum (case when vq.vote ='UP_VOTE' then 1 else -1 end ) " +
                "FROM Tag t join  t.questions q " +
                "JOIN VoteQuestion vq ON vq.question.id = q.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name").setParameter("tagList", tagList);


        Query answerVotes = entityManager.createQuery("SELECT  t.name" +
                ", SUM (CASE WHEN va.vote ='UP_VOTE' THEN 1 ELSE -1 END )" +
                "FROM Tag t JOIN  t.questions q " +
                "JOIN Answer a ON a.question.id = q.id " +
                "JOIN VoteAnswer va ON va.answer.id = a.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name").setParameter("tagList", tagList);

        List<Object[]> result1 = questionVotes.getResultList();
        List<Object[]> result2 = answerVotes.getResultList();
        result1.addAll(result2);
        return result1;
    }
}


