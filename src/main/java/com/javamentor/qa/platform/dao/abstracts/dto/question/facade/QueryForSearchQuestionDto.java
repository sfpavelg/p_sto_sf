package com.javamentor.qa.platform.dao.abstracts.dto.question.facade;

import javax.persistence.Query;
import java.util.Map;

public interface QueryForSearchQuestionDto {
     String editQueryByStringQueryAndParameters (String query, String value);
     Query setAllParametersForQueryByQueryAndValue(Query query, String value);
}
