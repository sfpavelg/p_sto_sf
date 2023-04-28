package com.javamentor.qa.platform.dao.impl.dto.question.pagination;

import com.javamentor.qa.platform.dao.abstracts.dto.question.pagination.QuestionDtoWithoutAnswerPaginationDao;
import com.javamentor.qa.platform.models.dto.question.QuestionViewDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

@Repository
public class QuestionDtoWithoutAnswerPaginationDaoImpl implements QuestionDtoWithoutAnswerPaginationDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<QuestionViewDto> getItems(Map<String, Object> param) {
        int itemsOnPageParam = (int) param.get("itemsOnPage");
        int itemsPositionParam = (int) param.get("currentPageNumber") * itemsOnPageParam - itemsOnPageParam;
        List<Long> trackedTag = (List<Long>) param.get("trackedTag");
        List<Long> ignoredTag = (List<Long>) param.get("ignoredTag");
        Query query = entityManager.createQuery(
                        "SELECT new com.javamentor.qa.platform.models.dto.question.pagination.QuestionViewDto( " +
                                "q.id, q.title , q.user.id, " +
                                "(SELECT coalesce(sum(rep.count),0) FROM Reputation rep WHERE rep.author.id = q.user.id), " +
                                "q.user.fullName, q.user.imageLink, q.description , " +
                                "(SELECT count(qw.question.id) FROM QuestionViewed qw WHERE qw.question.id = q.id), " +
                                "0L , " +
                                "(SELECT count(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'up') - " +
                                "(SELECT count(vq.question.id) FROM VoteQuestion vq WHERE vq.question.id = q.id AND vq.vote = 'down'), " +
                                "q.persistDateTime, q.lastUpdateDateTime ) " +
                                "FROM Question q " +
                                "LEFT JOIN Answer ans on ans.question.id = q.id " +
                                "WHERE ans.id is null " +
                                "and q.id in (select q.id from Question q join q.tags as tags where :trackedTag is null or tags.id in :trackedTag) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTag) " +
                                "ORDER BY q.id",
                        QuestionViewDto.class)
                .setParameter("trackedTag", trackedTag)
                .setParameter("ignoredTag", ignoredTag)
                .setMaxResults(itemsOnPageParam)
                .setFirstResult(itemsPositionParam);
        return (List<QuestionViewDto>) query.getResultList();
    }

    @Override
    public int getTotalResultCount(Map<String, Object> param) {
        List<Long> trackedTag = (List<Long>) param.get("trackedTag");
        List<Long> ignoredTag = (List<Long>) param.get("ignoredTag");
        Query query = entityManager.createQuery(
                        "SELECT q.id, ans.id FROM Question q LEFT JOIN Answer ans on ans.question.id = q.id " +
                                "WHERE ans.id is null " +
                                "and q.id in (select q.id from Question q join q.tags as tags where :trackedTag is null or tags.id in :trackedTag) " +
                                "and q.id not in (select q.id from Question q join q.tags as tags where tags.id in :ignoredTag) "
                )
                .setParameter("trackedTag", trackedTag)
                .setParameter("ignoredTag", ignoredTag);
        return query.getResultList().size();
    }

    @Override
    public Map<Long, List<TagDto>> getTagsMapByQuestionId(List<Long> listQuestionId) {
        List<Tuple> tags = entityManager.createQuery(
                        "select t.id as tag_id, " +
                                "t.name as tag_name, " +
                                "t.description as tag_description, " +
                                "q.id as question_id " +
                                "from Tag t join t.questions q where q.id in :id", Tuple.class)
                .setParameter("id", listQuestionId)
                .getResultList();
        Map<Long, List<TagDto>> tagsMap = new HashMap<>();

        tags.forEach(tupleList -> tagsMap.computeIfAbsent((Long) tupleList.get("question_id"), key -> new ArrayList<>())
                .add(new TagDto((Long) tupleList.get("tag_id"),
                        (String) tupleList.get("tag_name"),
                        (String) tupleList.get("tag_description"))));
        return tagsMap;
    }
}
