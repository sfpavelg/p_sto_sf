package com.javamentor.qa.platform.dao.impl.dto.question.facade;

import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.QueryForSearchQuestionDto;
import org.springframework.stereotype.Component;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Component
public class QueryForSearchQuestionDtoImpl implements QueryForSearchQuestionDto {
    @Override
    public String editQueryByStringQueryAndParameters(String query, Map<String, Object> parameters) {
        StringBuilder queryStringBuilder = new StringBuilder(query);
        List<String> text = (List<String>) parameters.get("text");
        List<String> tags = (List<String>) parameters.get("tags");
        //Добавление в queryStringBuilder набора элементов из List<String> text
        for (int i = 0; i < text.size(); i++) {
            queryStringBuilder.append(" and (:text").append(i).append(" is null or q.title like concat('%', :text").append(i).append(", '%'))");
        }
        //Добавление в queryStringBuilder набора элементов из List<String> tags
        for (int i =0; i < tags.size(); i++) {
            queryStringBuilder.append(" and (:tag").append(i).append(" is null or t.name like (:tag").append(i).append("))");
        }
        queryStringBuilder.append(" group by q.id, u.id, t.id");
        return queryStringBuilder.toString();
    }

    @Override
    public Query setParametersForQueryByQueryAndParameters(Query query, Map<String, Object> parameters) {
        List<String> text = (List<String>) parameters.get("text");
        List<String> tags = (List<String>) parameters.get("tags");
        query
            .setParameter("user", parameters.get("user"))
            .setParameter("answers", parameters.get("answers"))
            .setParameter("views", parameters.get("views"));
        //Установка параметров для элементов из List<String> text
        for (int i = 0; i < text.size(); i++) {
            query.setParameter("text" + i, text.get(i));
        }
        //Установка параметров для элементов из List<String> tags
        for (int i = 0; i < tags.size(); i++) {
            query.setParameter("tag" + i, tags.get(i));
        }
        return query;
    }
}
