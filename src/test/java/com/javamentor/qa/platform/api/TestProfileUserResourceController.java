package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestProfileUserResourceController extends AbstractTestApi {

    @Test
    @SqlGroup({
            @Sql(value = {"/script/TestProfileUserResourceController/testGetAllUserAuthorizedQuestions/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = {"/script/TestProfileUserResourceController/testGetAllUserAuthorizedQuestions/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void testGetAllUserAuthorizedQuestions() throws Exception {
        String jwt = getToken("0@gmail.com", "0pwd");

        // Check successful execution of request all UserProfileQuestionDto from authorized User
        this.mvc.perform(get("/api/user/profile/questions")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))

                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].title", Is.is("title1")))
                .andExpect(jsonPath("$[0].tags", hasSize(2)))
                .andExpect(jsonPath("$[0].tags[0].name", Is.is("name1")))
                .andExpect(jsonPath("$[0].tags[1].name", Is.is("name4")))
                .andExpect(jsonPath("$[0].countAnswer", Is.is(3)))

                .andExpect(jsonPath("$[1].questionId", Is.is(105)))
                .andExpect(jsonPath("$[1].title", Is.is("title6")))
                .andExpect(jsonPath("$[1].tags", hasSize(2)))
                .andExpect(jsonPath("$[1].tags[0].name", Is.is("name6")))
                .andExpect(jsonPath("$[1].tags[1].name", Is.is("name2")))
                .andExpect(jsonPath("$[1].countAnswer", Is.is(1)))

                .andExpect(jsonPath("$[2].questionId", Is.is(107)))
                .andExpect(jsonPath("$[2].title", Is.is("title8")))
                .andExpect(jsonPath("$[2].tags", hasSize(2)))
                .andExpect(jsonPath("$[2].tags[0].name", Is.is("name1")))
                .andExpect(jsonPath("$[2].tags[1].name", Is.is("name4")))
                .andExpect(jsonPath("$[2].countAnswer", Is.is(0)));

        // Check successful execution of request with questions that have no tags and no answers
        String emptyTagsQuestionUser = getToken("3@gmail.com", "3pwd");
        this.mvc.perform(get("/api/user/profile/questions")
                        .header("Authorization", "Bearer " + emptyTagsQuestionUser)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(4)))

                .andExpect(jsonPath("$[3].questionId", Is.is(112)))
                .andExpect(jsonPath("$[3].title", Is.is("title13")))
                .andExpect(jsonPath("$[3].tags", hasSize(0)))
                .andExpect(jsonPath("$[3].countAnswer", Is.is(0)));

        // Check successful execution of request with authenticated user who has no questions
        String emptyQuestionsUserJWT = getToken("4@gmail.com", "4pwd");
        this.mvc.perform(get("/api/user/profile/questions")
                        .header("Authorization", "Bearer " + emptyQuestionsUserJWT)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetUserRemovedQuestion/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetUserRemovedQuestion/After.sql"})
    })
    public void testGetUserRemovedQuestion() throws Exception {
        // Check successful execution of request with authenticated user without delete questions
        String JWT = getToken("4@gmail.com", "4pwd");
        this.mvc.perform(get("/api/user/profile/delete/questions")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));


        // Check successful execution of request user with removed questions
        String jwt1 = getToken("0@gmail.com", "0pwd");
        this.mvc.perform(get("/api/user/profile/delete/questions")
                        .header("Authorization", "Bearer " + jwt1)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))

                .andExpect(jsonPath("$[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$[0].title", Is.is("title1")))
                .andExpect(jsonPath("$[0].tags", hasSize(2)))
                .andExpect(jsonPath("$[0].tags[0].name", Is.is("name1")))
                .andExpect(jsonPath("$[0].tags[1].name", Is.is("name4")))
                .andExpect(jsonPath("$[0].countAnswer", Is.is(3)))

                .andExpect(jsonPath("$[1].questionId", Is.is(105)))
                .andExpect(jsonPath("$[1].title", Is.is("title6")))
                .andExpect(jsonPath("$[1].tags", hasSize(2)))
                .andExpect(jsonPath("$[1].tags[0].name", Is.is("name6")))
                .andExpect(jsonPath("$[1].tags[1].name", Is.is("name2")))
                .andExpect(jsonPath("$[1].countAnswer", Is.is(1)))

                .andExpect(jsonPath("$[2].questionId", Is.is(107)))
                .andExpect(jsonPath("$[2].title", Is.is("title8")))
                .andExpect(jsonPath("$[2].tags", hasSize(2)))
                .andExpect(jsonPath("$[2].tags[0].name", Is.is("name1")))
                .andExpect(jsonPath("$[2].tags[1].name", Is.is("name4")))
                .andExpect(jsonPath("$[2].countAnswer", Is.is(0)));

    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetAllAuthorizedUserAnswersPerWeek/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetAllAuthorizedUserAnswersPerWeek/After.sql"})
    })
    public void testGetAllAuthorizedUserAnswersPerWeek() throws Exception {
        String jwt = getToken("0@gmail.com", "0pwd");

        // Check successful execution of request to get the count of answers from users per week
        this.mvc.perform(get("/api/user/profile/question/week")
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(5)));

        // Check successful execution of request to get the count of answers from users who haven't answered per week
        String emptyAnswersUserToken = getToken("4@gmail.com", "4pwd");
        this.mvc.perform(get("/api/user/profile/question/week")
                        .header("Authorization", "Bearer " + emptyAnswersUserToken))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
    }

    @Test
    @Sql(value = {"/script/TestProfileUserResourceController/testAddGroupOfBookmarks/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestProfileUserResourceController/testAddGroupOfBookmarks/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddGroupOfBookmarks() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");
        //���� �� ���������� ������ ��������
        this.mvc.perform(post("/api/user/profile/bookmark/group")
                        .header("Authorization", "Bearer " + token)
                        .content("BookmarkTitle"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.userId", Is.is(100)))
                .andExpect(jsonPath("$.title", Is.is("BookmarkTitle")));
        //���� �� ���������� ������ �������� � ������������� ����������
        this.mvc.perform(post("/api/user/profile/bookmark/group")
                        .header("Authorization", "Bearer " + token)
                        .content("BookmarkTitle"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Title already exist")));
    }


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetGroupBookmark/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetGroupBookmark/After.sql"})
    })
    public void testGetGroupBookmark() throws Exception {

        // successfully getting the bookmark group of the authenticated user
        String JWT = getToken("0@gmail.com", "0pwd");

        this.mvc.perform(get("/api/user/profile/bookmark/group")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].title", Is.is("title_100")))

                .andExpect(jsonPath("$[1].id", Is.is(101)))
                .andExpect(jsonPath("$[1].title", Is.is("title_101")));

        //checking for the absence of an authenticated user's bookmark group
        String JWT2 = getToken("2@gmail.com", "2pwd");

        this.mvc.perform(get("/api/user/profile/bookmark/group")
                        .header("Authorization", "Bearer " + JWT2)
                        .contentType(MediaType.APPLICATION_JSON))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$", hasSize(0)));

        //user not authenticated
        this.mvc.perform(post("/api/user/profile/bookmark/group"))

                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileVotesInfo/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileVotesInfo/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetUserProfileVotesInfo() throws Exception {
        String token = getToken("1000@gmail.com", "44pwd");

        //success adding ignored tag and returning TagDto
        this.mvc.perform(get("/api/user/profile/vote").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countVoteUp", Is.is(2)))
                .andExpect(jsonPath("$.countVoteDown", Is.is(0)))
                .andExpect(jsonPath("$.countVoteQuestion", Is.is(1)))
                .andExpect(jsonPath("$.countVoteAnswer", Is.is(1)))
                .andExpect(jsonPath("$.countVoteMonth", Is.is(0)));
    }

}
