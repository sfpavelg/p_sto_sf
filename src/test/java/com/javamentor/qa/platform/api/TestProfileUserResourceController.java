package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
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
        String token1 = getToken("1000@gmail.com", "44pwd");
        String token2 = getToken("1001@gmail.com", "44pwd");
        String token3 = getToken("1002@gmail.com", "44pwd");
        String token4 = getToken("1003@gmail.com", "44pwd");

        //success with (1up vote_on_question) and (1up vote_on_answer)
        this.mvc.perform(get("/api/user/profile/vote").header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countVoteUp", Is.is(2)))
                .andExpect(jsonPath("$.countVoteDown", Is.is(0)))
                .andExpect(jsonPath("$.countVoteQuestion", Is.is(1)))
                .andExpect(jsonPath("$.countVoteAnswer", Is.is(1)))
                .andExpect(jsonPath("$.countVoteMonth", Is.is(0)));

        //success with (1up, 1down vote_on_question) and (1up, 1down vote_on_answer + 1 current date)
        this.mvc.perform(get("/api/user/profile/vote").header("Authorization", "Bearer " + token2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countVoteUp", Is.is(2)))
                .andExpect(jsonPath("$.countVoteDown", Is.is(2)))
                .andExpect(jsonPath("$.countVoteQuestion", Is.is(2)))
                .andExpect(jsonPath("$.countVoteAnswer", Is.is(2)))
                .andExpect(jsonPath("$.countVoteMonth", Is.is(1)));

        //success with (2up vote_on_question) and (2up vote_on_answer)
        this.mvc.perform(get("/api/user/profile/vote").header("Authorization", "Bearer " + token3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countVoteUp", Is.is(4)))
                .andExpect(jsonPath("$.countVoteDown", Is.is(0)))
                .andExpect(jsonPath("$.countVoteQuestion", Is.is(2)))
                .andExpect(jsonPath("$.countVoteAnswer", Is.is(2)))
                .andExpect(jsonPath("$.countVoteMonth", Is.is(0)));

        //success with 0 votes
        this.mvc.perform(get("/api/user/profile/vote").header("Authorization", "Bearer " + token4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.countVoteUp", Is.is(0)))
                .andExpect(jsonPath("$.countVoteDown", Is.is(0)))
                .andExpect(jsonPath("$.countVoteQuestion", Is.is(0)))
                .andExpect(jsonPath("$.countVoteAnswer", Is.is(0)))
                .andExpect(jsonPath("$.countVoteMonth", Is.is(0)));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetUserTagsWithRating/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestProfileUserResourceController/testGetUserTagsWithRating/After.sql"})
    })
    public void testGetUserTagsWithRating() throws Exception {
        String JWT = getToken("100@gmail.com", "0pwd");

//      positive get tests
        this.mvc.perform(get("/api/user/profile/tag")
                .header("Authorization", "Bearer " + JWT)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$[0].tagName", Is.is("tag with id 109")))
                .andExpect(jsonPath("$[0].countVoteTag", Is.is(-4)))
                .andExpect(jsonPath("$[0].countAnswerQuestion", Is.is(1)))
                .andExpect(jsonPath("$[1].tagName", Is.is("tag with id 100")))
                .andExpect(jsonPath("$[1].countVoteTag", Is.is(2)))
                .andExpect(jsonPath("$[1].countAnswerQuestion", Is.is(3)));


        String JWTUser105 = getToken("email6@domain.com", "0pwd");

        this.mvc.perform(get("/api/user/profile/tag")
                        .header("Authorization", "Bearer " + JWTUser105)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].tagName", Is.is("tag with id 100")))
                .andExpect(jsonPath("$[0].countVoteTag", Is.is(2)))
                .andExpect(jsonPath("$[0].countAnswerQuestion", Is.is(1)));
    }

    @Test
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfile/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfile/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetUserProfile() throws Exception {
        String JWT = getToken("100@gmail.com", "0pwd");

        //Success
        this.mvc.perform(get("/api/user/profile/{userId}",100)
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.reputation", Is.is(6)))
                .andExpect(jsonPath("$.countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.countView", Is.is(2)))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[0].tagId", Is.is(101)))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[0].name", Is.is("tag with id 101")))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[0].countMessage", Is.is(2)))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[1].tagId", Is.is(102)))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[1].name", Is.is("tag with id 102")))
                .andExpect(jsonPath("$.userTagFavoriteDtoList[1].countMessage", Is.is(1)));

        //not found User
        this.mvc.perform(get("/api/user/profile/{userId}", 106).header("Authorization", "Bearer " + JWT))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("User with this id:106 not found")));
    }

    @Test
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileReputation/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileReputation/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetUserProfileReputation() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        // first page
        this.mvc.perform(get("/api/user/profile/reputation")
                .header("Authorization", "Bearer " + token)
                .param("items", "5")
                .param("currentPage", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items.length()", Is.is(5)))
                .andExpect(jsonPath("$.items[0].countReputation", Is.is(3)))
                .andExpect(jsonPath("$.items[0].persistDate", Is.is("2023-01-21T15:21:03.527867")))
                .andExpect(jsonPath("$.items[0].action", Is.is("UP_VOTE")))
                .andExpect(jsonPath("$.items[1].countReputation", Is.is(3)))
                .andExpect(jsonPath("$.items[1].persistDate", Is.is("2023-01-21T15:21:03.527867")))
                .andExpect(jsonPath("$.items[1].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[2].countReputation", Is.is(2)))
                .andExpect(jsonPath("$.items[2].persistDate", Is.is("2023-01-20T15:21:03.527867")))
                .andExpect(jsonPath("$.items[2].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[3].countReputation", Is.is(2)))
                .andExpect(jsonPath("$.items[3].persistDate", Is.is("2023-01-20T15:21:03.527867")))
                .andExpect(jsonPath("$.items[3].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[4].countReputation", Is.is(1)))
                .andExpect(jsonPath("$.items[4].persistDate", Is.is("2023-01-19T15:21:03.527867")))
                .andExpect(jsonPath("$.items[4].action", Is.is("CREATE_QUESTION")));

        // second page
        this.mvc.perform(get("/api/user/profile/reputation")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("currentPage", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.items.length()", Is.is(1)))
                .andExpect(jsonPath("$.items[0].countReputation", Is.is(1)))
                .andExpect(jsonPath("$.items[0].persistDate", Is.is("2023-01-18T15:21:03.527867")))
                .andExpect(jsonPath("$.items[0].action", Is.is("UP_VOTE")));

        // default (items = 10, currentPage = 1)
        this.mvc.perform(get("/api/user/profile/reputation")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items.length()", Is.is(6)))
                .andExpect(jsonPath("$.items[0].countReputation", Is.is(3)))
                .andExpect(jsonPath("$.items[0].persistDate", Is.is("2023-01-21T15:21:03.527867")))
                .andExpect(jsonPath("$.items[0].action", Is.is("UP_VOTE")))
                .andExpect(jsonPath("$.items[1].countReputation", Is.is(3)))
                .andExpect(jsonPath("$.items[1].persistDate", Is.is("2023-01-21T15:21:03.527867")))
                .andExpect(jsonPath("$.items[1].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[2].countReputation", Is.is(2)))
                .andExpect(jsonPath("$.items[2].persistDate", Is.is("2023-01-20T15:21:03.527867")))
                .andExpect(jsonPath("$.items[2].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[3].countReputation", Is.is(2)))
                .andExpect(jsonPath("$.items[3].persistDate", Is.is("2023-01-20T15:21:03.527867")))
                .andExpect(jsonPath("$.items[3].action", Is.is("DOWN_VOTE")))
                .andExpect(jsonPath("$.items[4].countReputation", Is.is(1)))
                .andExpect(jsonPath("$.items[4].persistDate", Is.is("2023-01-19T15:21:03.527867")))
                .andExpect(jsonPath("$.items[4].action", Is.is("CREATE_QUESTION")))
                .andExpect(jsonPath("$.items[5].countReputation", Is.is(1)))
                .andExpect(jsonPath("$.items[5].persistDate", Is.is("2023-01-18T15:21:03.527867")))
                .andExpect(jsonPath("$.items[5].action", Is.is("UP_VOTE")));
    }

    @Test
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileCommentDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestProfileUserResourceController/testGetUserProfileCommentDto/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetUserProfileCommentDto() throws Exception {
        String JWT = getToken("100@gmail.com", "0pwd");

        //Success test
        this.mvc.perform(get("/api/user/profile/comment")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(3)))
                //Comment to question and answer
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].comment", Is.is("comment1")))
                .andExpect(jsonPath("$.items[0].persistDate", Is.is("2023-01-19T15:21:03.527866")))
                .andExpect(jsonPath("$.items[0].questionId", Is.is(101)))
                .andExpect(jsonPath("$.items[0].answerId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].commentType", Is.is("QUESTION")))
                //Comment to question with no answer
                .andExpect(jsonPath("$.items[1].id", Is.is(104)))
                .andExpect(jsonPath("$.items[1].comment", Is.is("comment5")))
                .andExpect(jsonPath("$.items[1].persistDate", Is.is("2023-01-19T15:21:03.527866")))
                .andExpect(jsonPath("$.items[1].questionId", Is.is(102)))
                .andExpect(jsonPath("$.items[1].answerId", IsNull.nullValue()))
                .andExpect(jsonPath("$.items[1].commentType", Is.is("QUESTION")))
                //Comment to answer
                .andExpect(jsonPath("$.items[2].id", Is.is(108)))
                .andExpect(jsonPath("$.items[2].comment", Is.is("comment9")))
                .andExpect(jsonPath("$.items[2].persistDate", Is.is("2023-01-19T15:21:03.527866")))
                .andExpect(jsonPath("$.items[2].questionId", Is.is(101)))
                .andExpect(jsonPath("$.items[2].answerId", Is.is(105)))
                .andExpect(jsonPath("$.items[2].commentType", Is.is("ANSWER")))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));

        String nJWT = getToken("105@gmail.com", "0pwd");

        //User has no comments at all
        this.mvc.perform(get("/api/user/profile/comment")
                        .header("Authorization", "Bearer " + nJWT)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(0)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(0)))
                .andExpect(jsonPath("$.items", hasSize(0)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)));
    }
}
