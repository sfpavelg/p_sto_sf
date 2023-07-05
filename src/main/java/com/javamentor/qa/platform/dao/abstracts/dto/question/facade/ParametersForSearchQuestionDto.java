package com.javamentor.qa.platform.dao.abstracts.dto.question.facade;

import java.util.Map;

public interface ParametersForSearchQuestionDto {
    Map<String, Object> getAllParametersByValue (String value);
}
