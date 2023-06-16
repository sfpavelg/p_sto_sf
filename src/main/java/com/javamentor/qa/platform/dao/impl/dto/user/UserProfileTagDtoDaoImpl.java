package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserProfileTagDtoDaoImpl implements UserProfileTagDtoDao {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<UserProfileTagDto> getUserProfileTagDtoByUserId(Long userId) {
//        Query query = entityManager.createNativeQuery("SELECT tag.name, CONCAT(COUNT(CASE WHEN votes_on_answers.vote = 'UP_VOTE' THEN 1 END) - COUNT(CASE WHEN votes_on_answers.vote = 'DOWN_VOTE' THEN 1 END)) AS countVoteTag " +
//                "FROM votes_on_answers " +
//                "         JOIN answer " +
//                "              ON votes_on_answers.answer_id = answer.id " +
//                "         JOIN question_has_tag " +
//                "              ON answer.question_id = question_has_tag.question_id " +
//                "         JOIN tag " +
//                "              ON question_has_tag.tag_id = tag.id " +
//                "WHERE votes_on_answers.user_id = :userId " +
//                "GROUP BY tag.name ").setParameter("userId", userId);
//        Query query1 = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileTagDto (" +
//                "t.name,  " +
//                " concat(count(case when v.vote = 'UP_VOTE' then 1 end) - count(case when v.vote = 'DOWN_VOTE' then 1 end)) as countVoteTag )" +
//                "FROM VoteAnswer v " +
//                "JOIN Answer a " +
//                "ON v.answer.id = a.id " +
//                "JOIN Question.tags qt " +
//                "ON a.question.id = qt.id " +
//                "JOIN Tag t " +
//                "ON qt.id = t.id " +
//                "WHERE v.id = :userId " +
//                "GROUP BY t.name ", UserProfileTagDto.class).setParameter("userId", userId);
//List <UserProfileTagDto>list=query1.getResultList();
//        list.stream().forEach(userProfileTagDto -> System.out.println(userProfileTagDto));

        Query query = entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.user.UserProfileTagDto( " +
                " t.name) " +
                "FROM Tag t " +
                "WHERE t.id = :userId ", UserProfileTagDto.class).setParameter("userId", userId);
//        select tag.name,
//                concat(count(case when votes_on_answers.vote = 'UP_VOTE' then 1 end) - count(case when votes_on_answers.vote = 'DOWN_VOTE' then 1 end)) as up_votes
//        from votes_on_answers
//        join answer
//        on votes_on_answers.answer_id = answer.id
//        join question_has_tag
//        on answer.question_id = question_has_tag.question_id
//        join tag
//        on question_has_tag.tag_id = tag.id
//        where votes_on_answers.user_id = 1
//        group by tag.name

        return query.getResultList();
    }
}
