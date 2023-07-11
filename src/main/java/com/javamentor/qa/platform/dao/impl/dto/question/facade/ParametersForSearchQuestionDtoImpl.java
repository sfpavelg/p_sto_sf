package com.javamentor.qa.platform.dao.impl.dto.question.facade;

import com.javamentor.qa.platform.dao.abstracts.dto.question.facade.ParametersForSearchQuestionDto;
import org.springframework.stereotype.Component;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class ParametersForSearchQuestionDtoImpl implements ParametersForSearchQuestionDto {

    public List<String> getTextByValue(String text) {
        return List.of(text.replaceAll("\\[.+?]", "")
                .replaceAll("user:\\d+", "")
                .replaceAll("answers:\\d+", "")
                .replaceAll("views:\\d+", "")
                .trim()
                .replaceAll("\\s+", " ").split(" ", 0));
    }
    public Long getUserIdByValue(String text) {
        Matcher matcher = Pattern.compile("user:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }
    public Long getViewsByValue(String text) {
        Matcher matcher = Pattern.compile("views:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }
    public Long getAnswersByValue(String text) {
        Matcher matcher = Pattern.compile("answers:\\d+").matcher(text);
        if (matcher.find()) {
            if (!text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", "").isEmpty()) {
                return Long.valueOf(text.substring(matcher.start(), matcher.end()).replaceAll("\\D+", ""));
            }
        }
        return null;
    }
    public List<String> getTagsByValue(String text) {
        int end = 0;
        List<String> tags = new ArrayList<>();
        Matcher matcher = Pattern.compile("\\[.+?]").matcher(text);
        while (matcher.find(end)) {
            tags.add(text.substring(matcher.start(), matcher.end()).replaceAll("\\[|\\]", ""));
            end = matcher.end();
        }
        return tags;
    }

    public StringBuilder addViewsParameterForQuery(StringBuilder query) {
        query.append(" and ((select count (qw.question.id) from QuestionViewed qw where qw.question.id = q.id) >= :views or :views is null)");
        return query;
    }
    public StringBuilder addAnswersParameterForQuery (StringBuilder query) {
        query.append(" and ((select count (a.question.id) from Answer a where a.question.id = q.id) >= :answers or :answers is null)");
        return query;
    }
    public StringBuilder addTagsParametersForQuery (StringBuilder query, String value) {
        List<String> tags = getTagsByValue(value);
        for (int i =0; i < tags.size(); i++) {
            query.append(" and (:tag").append(i).append(" is null or t.name like (:tag").append(i).append("))");
        }
        return query;
    }

    public StringBuilder addTextParametersForQuery (StringBuilder query, String value) {
        List<String> text = getTextByValue(value);
        for (int i = 0; i < text.size(); i++) {
            query.append(" and (:text").append(i).append(" is null or q.title like concat('%', :text").append(i).append(", '%'))");
        }
        return query;
    }

    public Query setTextParametersForQuery (Query query, String value) {
        List<String> text = getTextByValue(value);
        for (int i = 0; i < text.size(); i++) {
            query.setParameter("text" + i, text.get(i));
        }
        return query;
    }
    public Query setTagsParametersForQuery (Query query, String value) {
        List<String> tags = getTagsByValue(value);
        for (int i = 0; i < tags.size(); i++) {
            query.setParameter("tag" + i, tags.get(i));
        }
        return query;
    }
}
