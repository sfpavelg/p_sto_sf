package com.javamentor.qa.platform.dao.impl.dto.question.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoByTagPaginationDao;
import com.javamentor.qa.platform.models.dto.question.QuestionDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Map;

@Repository
public class QuestionDtoByTagPaginationDaoImpl implements QuestionDtoByTagPaginationDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;

//        entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.question.QuestionDto (" +
//                "q.id, " +
//                "q.title, " +
//                "q.user.id, " +
//                "(SELECT COALESCE(SUM(r.count),0) FROM Reputation r WHERE r.author.id = q.user.id), " +
//                "q.user.fullName, " +
//                "q.user.imageLink, " +
//                "q.description, " +
//                "(SELECT COUNT (qw.question.id) FROM QuestionViewed qw WHERE qw.question.id = q.id), " +
//                "(SELECT COUNT (a.question.id) FROM Answer a WHERE a.question.id = q.id), " +
//                "(SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'UP_VOTE') - " +
//                "(SELECT COUNT(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'DOWN_VOTE'), " +
//                "q.persistDateTime, " +
//                "q.lastUpdateDateTime, " +
//                "(select vq.vote from VoteQuestion vq where vq.user.id = :userId and vq.question.id = q.id)) " +
//                "FROM Question q " +
//                "LEFT JOIN User u ON u.id = q.user.id " +
//                "LEFT JOIN Reputation r ON u.id = r.author.id " +
//                "where q.id = :id " +
//                " group by q.id, u.id", QuestionDto.class).setParameter());


     return  entityManager.createQuery("SELECT new com.javamentor.qa.platform.models.dto.question.QuestionDto (" +
                "q.id, " +
                "q.title , " +
                "u.id, " +
                "coalesce(sum(r.count),0), " +
                "u.fullName, " +
                "u.imageLink, " +
                "q.description , " +
                "(select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id), " +
                "(select count (a.question.id) from Answer a where a.question.id = q.id), " +
                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'up') - " +
                "(select count(vq.question.id) from VoteQuestion vq where vq.question.id = q.id and vq.vote = 'down'), " +
                "q.persistDateTime, " +
                "q.lastUpdateDateTime, " +
                "(select vq.vote from VoteQuestion vq where vq.user.id = :userId and vq.question.id = q.id)) " +
                "FROM Tag t " +
                "LEFT JOIN t.questions q " +
                "LEFT JOIN User u ON u.id = q.user.id " +
                "LEFT JOIN Reputation r ON u.id = r.author.id " +
                "where  t.name = :tagName " +
                " group by q.id, u.id", QuestionDto.class)
                .setParameter("userId", param.get("userId"))
                .setParameter("tagName", param.get("tagName"))
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam)
                .getResultList();

    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        return Math.toIntExact((Long) entityManager
                .createQuery("SELECT COUNT (t.name) FROM Tag t JOIN t.questions q WHERE t.name = :tagName ")
                .setParameter("tagName",param.get("name")).getSingleResult());

    }
}
