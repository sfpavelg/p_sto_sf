package com.javamentor.qa.platform.dao.impl.dto.question.facade;

import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.QueryForSearchQuestionDto;
import org.springframework.stereotype.Component;
import javax.persistence.Query;

@Component
public class QueryForSearchQuestionDtoImpl extends ParametersForSearchQuestionDtoImpl implements QueryForSearchQuestionDto {
    @Override
    public String editQueryByStringQueryAndParameters(String query, String value) {
        StringBuilder queryStringBuilder = new StringBuilder(query);
        queryStringBuilder = addAnswersParameterForQuery(queryStringBuilder);
        queryStringBuilder = addViewsParameterForQuery(queryStringBuilder);
        queryStringBuilder = addTextParametersForQuery(queryStringBuilder, value);
        queryStringBuilder = addTagsParametersForQuery(queryStringBuilder,value);
        queryStringBuilder.append(" group by q.id, u.id, t.id");
        return queryStringBuilder.toString();
    }

    @Override
    public Query setAllParametersForQueryByQueryAndValue(Query query, String value) {
        query
            .setParameter("user", getUserIdByValue(value))
            .setParameter("answers", getAnswersByValue(value))
            .setParameter("views", getViewsByValue(value));
        query = setTagsParametersForQuery(query, value);
        query = setTextParametersForQuery(query, value);
        return query;
    }
}
