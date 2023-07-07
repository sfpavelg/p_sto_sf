package com.javamentor.qa.platform.dao.abstracts.dto.question.facade;

import javax.persistence.Query;
import java.util.List;
import java.util.Map;

public interface ParametersForSearchQuestionDto {
    List<String> getTextByValue(String text);
    Long getUserIdByValue(String text);
    Long getViewsByValue(String text);
    Long getAnswersByValue(String text);
    List<String> getTagsByValue(String text);
    StringBuilder addViewsParameterForQuery(StringBuilder query);
    StringBuilder addAnswersParameterForQuery (StringBuilder query);
    StringBuilder addTagsParametersForQuery (StringBuilder query, String value);
    StringBuilder addTextParametersForQuery (StringBuilder query, String value);
    Query setTextParametersForQuery (Query query, String value);
    Query setTagsParametersForQuery (Query query, String value);
}
