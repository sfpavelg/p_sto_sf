package com.javamentor.qa.platform.dao.impl.dto.question.facade;

import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.ParametersForSearchQuestionDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParametersForSearchQuestionDtoImpl implements ParametersForSearchQuestionDto {
    public Map<String, Object> getAllParametersByValue(String value) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("text", getTextByValue(value));
        parameters.put("tags", getTagsByValue(value));
        parameters.put("user", getUserIdByValue(value));
        parameters.put("answers", getAnswersByValue(value));
        parameters.put("views", getViewsByValue(value));
        return parameters;
    }

    private List<String> getTextByValue(String text) {
        return List.of(text.replaceAll("\\[.+?]", "")
                .replaceAll("user:\\d+", "")
                .replaceAll("answers:\\d+", "")
                .replaceAll("views:\\d+", "")
                .trim()
                .replaceAll("\\s+", " ").split(" ", 0));
    }
    private Long getUserIdByValue(String text) {
        Matcher matcher = Pattern.compile("user:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }
    private Long getViewsByValue(String text) {
        Matcher matcher = Pattern.compile("views:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }
    private Long getAnswersByValue(String text) {
        Matcher matcher = Pattern.compile("answers:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }

    private List<String> getTagsByValue(String value) {
        int end = 0;
        List<String> tags = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\[.+?]").matcher(value);
        while (matcher.find(end)) {
            tags.add(value.substring(matcher.start(), matcher.end()).replaceAll("\\[|\\]", ""));
            end = matcher.end();
        }
        return tags;
    }
}
