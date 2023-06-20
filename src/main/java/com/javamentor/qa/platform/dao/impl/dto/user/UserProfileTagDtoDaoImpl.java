package com.javamentor.qa.platform.dao.impl.dto.user;

import com.javamentor.qa.platform.dao.abstracts.dto.user.UserProfileTagDtoDao;
import com.javamentor.qa.platform.models.dto.user.UserProfileTagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;

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

//        Query query1 = entityManager.createNativeQuery("SELECT tag.name, CONCAT(COUNT(CASE WHEN votes_on_answers.vote = 'UP_VOTE' THEN 1 END) - COUNT(CASE WHEN votes_on_answers.vote = 'DOWN_VOTE' THEN 1 END)) AS up_votes " +
//                "FROM votes_on_answers " +
//                "         JOIN answer " +
//                "              ON votes_on_answers.answer_id = answer.id " +
//                "         JOIN question_has_tag " +
//                "              ON answer.question_id = question_has_tag.question_id " +
//                "         JOIN tag " +
//                "              ON question_has_tag.tag_id = tag.id " +
//                "WHERE votes_on_answers.user_id = ? " +
//                "GROUP BY tag.name").setParameter(1, userId);
//        List<Object[]>   lst1= (List<Object[]>) query1.getResultList();
//
//        for (int i = 0; i < lst1.size(); i++) {
//            Object[] o = lst1.get(i);
//            Optional<UserProfileTagDto> userProfileTagDto = result.stream().filter(dto -> dto.getTagName().equals(o[0].toString())).findFirst();
//            result.get(result.indexOf(userProfileTagDto.get())).setCountVoteTag(Long.valueOf(o[1].toString()));
////            result.get(result.indexOf(new UserProfileTagDto().getTagName().equals(o[0].toString()))).setCountAnswerQuestion(Long.valueOf(o[1].toString()));
//        }


        return result;
    }


    public List<UserProfileTagDto> getTagVotesByList(List<String> tagList) {
        Query questionVotes = entityManager.createQuery("SELECT t.name" +
                ", sum (case when vq.vote ='UP_VOTE' then 1 else -1 end ) " +
                "FROM Tag t join  t.questions q " +
                "JOIN VoteQuestion vq ON vq.question.id = q.id " +
                "WHERE t.name IN (:tagList) " +
                "GROUP BY t.name").setParameter("tagList", tagList );
        List<UserProfileTagDto> result = questionVotes.getResultList();

        return result;
    }

//    public List<UserProfileTagDto> countTagVotes (Long userId) {
//        List<UserProfileTagDto> result = new ArrayList<>();
//
////                Query query1 = entityManager.createNativeQuery("SELECT tag.name, CONCAT(COUNT(CASE WHEN votes_on_answers.vote = 'UP_VOTE' THEN 1 END) - COUNT(CASE WHEN votes_on_answers.vote = 'DOWN_VOTE' THEN 1 END)) AS up_votes " +
////                "FROM votes_on_answers " +
////                "         JOIN answer " +
////                "              ON votes_on_answers.answer_id = answer.id " +
////                "         JOIN question_has_tag " +
////                "              ON answer.question_id = question_has_tag.question_id " +
////                "         JOIN tag " +
////                "              ON question_has_tag.tag_id = tag.id " +
////                "WHERE votes_on_answers.user_id = ? " +
////                "GROUP BY tag.name").setParameter(1, userId);
////        List<Object[]>   lst1= (List<Object[]>) query1.getResultList();
//
//        Query query1 = entityManager.createNativeQuery("SELECT tag.name, " +
//                "       CONCAT(COUNT(CASE WHEN votes_on_questions.vote = 'UP_VOTE' THEN 1 END) - COUNT(CASE WHEN votes_on_questions.vote = 'DOWN_VOTE' THEN 1 END)) AS up_votes " +
//                "FROM votes_on_questions " +
//                "         JOIN question_has_tag " +
//                "              ON votes_on_questions.question_id = question_has_tag.question_id " +
//                "         JOIN tag " +
//                "              ON question_has_tag.tag_id = tag.id " +
//                "WHERE votes_on_questions.user_id = ? " +
//                "GROUP BY tag.name " +
//                "UNION ALL " +
//                "SELECT tag.name, " +
//                "       CONCAT(COUNT(CASE WHEN votes_on_answers.vote = 'UP_VOTE' THEN 1 END) - COUNT(CASE WHEN votes_on_answers.vote = 'DOWN_VOTE' THEN 1 END)) AS up_votes2 " +
//                "FROM votes_on_answers " +
//                "         JOIN answer " +
//                "              ON votes_on_answers.answer_id = answer.id " +
//                "         JOIN question_has_tag " +
//                "              ON answer.question_id = question_has_tag.question_id " +
//                "         JOIN tag " +
//                "              ON question_has_tag.tag_id = tag.id " +
//                "WHERE votes_on_answers.user_id = ? " +
//                "GROUP BY tag.name; ").setParameter(1, userId).setParameter(2, userId);
//
//        List<Object[]>   lst1= (List<Object[]>) query1.getResultList();
//
//        for (int i = 0; i < lst1.size(); i++) {
//            Object[] o = lst1.get(i);
//            Optional<UserProfileTagDto> userProfileTagDto = result.stream().filter(dto -> dto.getTagName().equals(o[0].toString())).findFirst();
//            if (userProfileTagDto.isPresent()) {
//                Long temp = result.get(result.indexOf(userProfileTagDto.get())).getCountVoteTag() + Long.valueOf(o[1].toString());
//                result.get(result.indexOf(userProfileTagDto.get())).setCountVoteTag(temp);
//            } else {
//                UserProfileTagDto tempUserProfile = new UserProfileTagDto(o[0].toString(), 0L);
//                tempUserProfile.setCountVoteTag(Long.valueOf(o[1].toString()));
//                result.add(tempUserProfile);
//            }
////            Optional<UserProfileTagDto> userProfileTagDto = result.stream().filter(dto -> dto.getTagName().equals(o[0].toString())).findFirst();
////            result.get(result.indexOf(userProfileTagDto.get())).setCountVoteTag(Long.valueOf(o[1].toString()));
////            result.get(result.indexOf(new UserProfileTagDto().getTagName().equals(o[0].toString()))).setCountAnswerQuestion(Long.valueOf(o[1].toString()));
//
//        }
//
//        return result;
//    }
}


