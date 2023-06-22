package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestQuestionAnswerController extends AbstractTestApi {

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestQuestionAnswerController/testGetAllByQuestionId/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestQuestionAnswerController/testGetAllByQuestionId/After.sql"})
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
                .andExpect(jsonPath("$[0].nickName", Is.is("nickname1")))
                .andExpect(jsonPath("$[1].id", Is.is(103)))
                .andExpect(jsonPath("$[1].userId", Is.is(101)))
                .andExpect(jsonPath("$[1].userReputation", Is.is(4)))
                .andExpect(jsonPath("$[1].questionId", Is.is(100)))
                .andExpect(jsonPath("$[1].body", Is.is("html_body4")))
                .andExpect(jsonPath("$[1].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[1].isHelpful", Is.is(true)))
                .andExpect(jsonPath("$[1].dateAccept", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$[1].countValuable", Is.is(0)))
                .andExpect(jsonPath("$[1].image", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$[1].nickName", Is.is("nickname2")));

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
                    value = {"/script/TestQuestionAnswerController/testDeleteAnswer/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestQuestionAnswerController/testDeleteAnswer/After.sql"})
    })
    public void testDeleteAnswer() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        // success (questionId does not make sense)
        this.mvc.perform(delete("/api/user/question/{questionId}/answer/{answerId}", 10000, 100).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from Answer a where a.id = 100 and a.isDeleted = true")
                .getResultList()
                .isEmpty())
                .isEqualTo(false);

        // answerId does not exist
        this.mvc.perform(delete("/api/user/question/{questionId}/answer/{answerId}", 10000, 10000).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @Sql(value = {"/script/TestQuestionAnswerController/testVoteForAnswer/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/script/TestQuestionAnswerController/testVoteForAnswer/After.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void voteForAnswer() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        // UpVote for 103 answer - no one has voted for him yet
        this.mvc.perform(post("/api/user/question/110/answer/103/upVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(1)));
        // reputation check after voting with the author of the vote
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        // reputation check after voting with the author of the answer
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(10)));

        // DownVote for answer 103 - existing UpVote is changed to DownVote
        this.mvc.perform(post("/api/user/question/110/answer/103/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        // reputation check after voting with the author of the vote
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        // reputation check after voting with the author of the answer
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));

        // repeated DownVote for answer 103 - the vote must not be duplicated and the result must remain the same
        this.mvc.perform(post("/api/user/question/110/answer/103/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        // reputation check after voting with the author of the vote
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        // reputation check after voting with the author of the answer
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));

        // UpVote for 104 answer already upvoted and initially rated 15 = ( 2*10 - 1*(-5) )
        this.mvc.perform(post("/api/user/question/110/answer/104/upVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(2)));
        // reputation check after voting with the author of the vote
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        // reputation check after voting with the author of the answer
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("Samara")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(25)));

        // DownVote for answer 104 - existing UpVote is changed to DownVote. Expected reputation 10
        this.mvc.perform(post("/api/user/question/110/answer/104/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("Samara")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(10)));

        // DownVote for your own answer 100
        this.mvc.perform(post("/api/user/question/110/answer/100/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));

    }
}
