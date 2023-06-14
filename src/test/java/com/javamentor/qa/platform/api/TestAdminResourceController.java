package com.javamentor.qa.platform.api;


import com.fasterxml.jackson.core.type.TypeReference;
import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.answer.AnswerDto;
import com.javamentor.qa.platform.models.entity.question.answer.Answer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

public class TestAdminResourceController extends AbstractTestApi {


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestAdminResourceController/testGetDeletedAnswersByUserId/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestAdminResourceController/testGetDeletedAnswersByUserId/After.sql"})
    })
    public void testGetDeletedAnswersByUserId() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        String jsonResponse = mvc.perform(MockMvcRequestBuilders.get("/api/admin/answer/delete").param("userId", "1002"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString();
        List<AnswerDto> answerDtoList = objectMapper.readValue(jsonResponse, new TypeReference<List<AnswerDto>>() {});

        List<Answer> answerList = em.createQuery("select a from Answer a where a.user.id = 1002 and a.isDeleted = true")
                .getResultList();

        StringBuilder a = new StringBuilder();
        for (Answer answer : answerList) {
            a.append(answer.getId());
        }
        StringBuilder b = new StringBuilder();
        for (Answer answer : answerList) {
            b.append(answer.getId());
        }
        boolean areIdsEqual = a.toString().contentEquals(b);

        Assertions.assertTrue(areIdsEqual);
    }

}
