package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


public class TestAnswerResourceController extends AbstractTestApi {

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/testGetAllByQuestionId/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/testGetAllByQuestionId/After.sql"})
    })
    public void testGetAllByQuestionId() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //success
        this.mvc.perform(get("/api/user/question/{id}/answer", 100).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(104)))
                .andExpect(jsonPath("$[0].userId", Is.is(100)))
                .andExpect(jsonPath("$[0].userReputation", Is.is(6)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].body", Is.is("html_body5")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[0].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$[0].image", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname1")));

        //success (with votes on answer)
        this.mvc.perform(get("/api/user/question/{id}/answer", 102).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(102)))
                .andExpect(jsonPath("$[0].userId", Is.is(104)))
                .andExpect(jsonPath("$[0].userReputation", Is.is(0)))
                .andExpect(jsonPath("$[0].questionId", Is.is(102)))
                .andExpect(jsonPath("$[0].body", Is.is("html_body3")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[0].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].countValuable", Is.is(2)))
                .andExpect(jsonPath("$[0].image", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname5")));

        //question with no answers
        this.mvc.perform(get("/api/user/question/{id}/answer", 103).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(0)));

        //wrong id
        this.mvc.perform(get("/api/user/question/{id}/answer", 111).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());

        //null results in DB (only possible fields like imageLink, rep and valuable)
        this.mvc.perform(get("/api/user/question/{id}/answer", 104).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].userId", Is.is(102)))
                .andExpect(jsonPath("$[0].userReputation", Is.is(5)))
                .andExpect(jsonPath("$[0].questionId", Is.is(104)))
                .andExpect(jsonPath("$[0].body", Is.is("html_body1")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[0].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$[0].image", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname3")));
    }


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/testDeleteAnswer/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/testDeleteAnswer/After.sql"})
    })
    public void testDeleteAnswer() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //success
        this.mvc.perform(get("/api/user/question/{id}/answer", 100).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(104)))
                .andExpect(jsonPath("$[0].userId", Is.is(100)))
                .andExpect(jsonPath("$[0].userReputation", Is.is(6)))
                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].body", Is.is("html_body5")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[0].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$[0].image", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname1")));

        //success with votes on answer and with null results (only possible fields like imageLink, rep, counts of answers and valuable)
        this.mvc.perform(get("/api/user/question/{id}/answer", 102).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(102)))
                .andExpect(jsonPath("$[0].userId", Is.is(104)))
                .andExpect(jsonPath("$[0].userReputation", Is.is(0)))
                .andExpect(jsonPath("$[0].questionId", Is.is(102)))
                .andExpect(jsonPath("$[0].body", Is.is("html_body3")))
                .andExpect(jsonPath("$[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[0].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$[0].image", IsNull.nullValue()))
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname5")));

        //question with no answers
        this.mvc.perform(get("/api/user/question/{id}/answer", 103).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(0)));

        //wrong id
        this.mvc.perform(get("/api/user/question/{id}/answer", 111).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound());
    }
}
