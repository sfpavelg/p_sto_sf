package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestChatResourceController extends AbstractTestApi {

    @Test
    @Sql(value = {"/script/TestChatResourceController/testGetMessagesBySingleChatIdOrderNew/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testGetMessagesBySingleChatIdOrderNew/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void TestGetMessagesBySingleChatIdOrderNew() throws Exception {
        String token = getToken("email5@domain.com", "password");

//        Successfully test's
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(7)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].message", Is.is("user 101 message for tests")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].message", Is.is("user 101 message for tests")))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[2].id", Is.is(103)))
                .andExpect(jsonPath("$.items[2].message", Is.is("user 101 message for tests")))
                .andExpect(jsonPath("$.items[2].imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[3].id", Is.is(104)))
                .andExpect(jsonPath("$.items[3].message", Is.is("user 102 message for tests")))
                .andExpect(jsonPath("$.items[3].imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.items[4].id", Is.is(105)))
                .andExpect(jsonPath("$.items[4].message", Is.is("user 102 message for tests")))
                .andExpect(jsonPath("$.items[4].imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.items[5].id", Is.is(106)))
                .andExpect(jsonPath("$.items[5].message", Is.is("user 102 message for tests")))
                .andExpect(jsonPath("$.items[5].imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.items[6].id", Is.is(107)))
                .andExpect(jsonPath("$.items[6].message", Is.is("user 102 message for tests")))
                .andExpect(jsonPath("$.items[6].imageLink", Is.is("http://imagelink2.com")));
//          Empty list test
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 789)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is4xxClientError());

//          Wrong id test
        this.mvc.perform(get("/api/user/chat/{id}/single/message", "ada")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());

//          Pagination positive test
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 102)
                .header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
                .param("page", "2")
                .param("items", "2"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(117)))
                .andExpect(jsonPath("$.items[0].message", Is.is("user 105 message for tests")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.items[1].id", Is.is(118)))
                .andExpect(jsonPath("$.items[1].message", Is.is("user 106 message for tests")))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is("http://imagelink6.com")));
//        Pagination  positive test 1 page 1 element
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 102)
                        .header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("items", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(6)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(115)))
                .andExpect(jsonPath("$.items[0].message", Is.is("user 105 message for tests")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("http://imagelink5.com")));
//        Pagination  negative test wrong page
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 102)
                .header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
                .param("page", "0")
                .param("items", "1"))
                .andExpect(status().isBadRequest());
//        Pagination  negative test wrong items
        this.mvc.perform(get("/api/user/chat/{id}/single/message", 102)
                        .header("Authorization", "Bearer " + token).contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("items", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = {"/script/TestChatResourceController/testGetSingleChatDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testGetSingleChatDto/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetSingleChatDto() throws Exception {
        String token = getToken("email1@domain.com", "password");

        //        Successfully test's
        this.mvc.perform(get("/api/user/chat/single")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(100)))
                .andExpect(jsonPath("$[0].name", Is.is("name2")))
                .andExpect(jsonPath("$[0].image", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$[0].lastMessage", Is.is("user 102 LAST message for tests")))
                .andExpect(jsonPath("$[1].id", Is.is(101)))
                .andExpect(jsonPath("$[1].name", Is.is("name4")))
                .andExpect(jsonPath("$[1].image", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$[1].lastMessage", Is.is("user 101 LAST message for tests")));

        //          Empty list test
        token = getToken("email3@domain.com", "password");
        this.mvc.perform(get("/api/user/chat/single")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().is4xxClientError());
    }

}
