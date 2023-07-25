package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.entity.chat.GroupChat;
import com.javamentor.qa.platform.models.entity.chat.SingleChat;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.List;

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
    @Sql(value = {"/script/TestChatResourceController/testGetChatBySearch/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testGetChatBySearch/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetChatBySearch() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");
        //Тест на поиск чата по слову "JavaScript" - 1 чат
        this.mvc.perform(get("/api/user/chat/?value=Script")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(101)))
                .andExpect(jsonPath("$.[0].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:41:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов по слову "Java" - должно выйти 2 чата
        this.mvc.perform(get("/api/user/chat/?value=Java")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 0")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:42:33.176")))
                .andExpect(jsonPath("$.[1].id", Is.is(101)))
                .andExpect(jsonPath("$.[1].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.[1].lastMessage", Is.is("Hello 1")))
                .andExpect(jsonPath("$.[1].persistDateTimeLastMessage", Is.is("2023-06-18T13:41:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов с юзером
        this.mvc.perform(get("/api/user/chat/?value=nickname1")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(104)))
                .andExpect(jsonPath("$.[0].name", Is.is("nickname1")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 100id")))
                .andExpect(jsonPath("$.[0].image", Is.is("https://img.com/1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T15:50:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов по запросу "nick" - результат 2 чата с юзерами
        this.mvc.perform(get("/api/user/chat/?value=nick")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(104)))
                .andExpect(jsonPath("$.[0].name", Is.is("nickname1")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 100id")))
                .andExpect(jsonPath("$.[0].image", Is.is("https://img.com/1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T15:50:33.174")))
                .andExpect(jsonPath("$.[1].id", Is.is(105)))
                .andExpect(jsonPath("$.[1].name", Is.is("nickname2")))
                .andExpect(jsonPath("$.[1].lastMessage", Is.is("Hello 100id, i am 102id")))
                .andExpect(jsonPath("$.[1].image", Is.is("https://img.com/2")))
                .andExpect(jsonPath("$.[1].persistDateTimeLastMessage", Is.is("2023-06-18T14:44:33.174")))
                .andExpect(status().isOk());
        // Тест на пустой запрос. Должен вывести все чаты в которых состоит юзер.
        this.mvc.perform(get("/api/user/chat/?value=")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(6)));

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
        // there are no single chats
        token = getToken("email3@domain.com", "password");
        this.mvc.perform(get("/api/user/chat/single")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", Matchers.hasSize(0)));

        //there is no last message
        token = getToken("email7@domain.com", "password");
        this.mvc.perform(get("/api/user/chat/single")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(103)))
                .andExpect(jsonPath("$[0].name", Is.is("name8")))
                .andExpect(jsonPath("$[0].image", Is.is("http://imagelink8.com")))
                .andExpect(jsonPath("$[0].lastMessage").value(IsNull.nullValue()))
                .andExpect(jsonPath("$[0].persistDateTimeLastMessage").value(IsNull.nullValue()));

        //there is no imageLink
        token = getToken("email9@domain.com", "password");
        this.mvc.perform(get("/api/user/chat/single")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Is.is(104)))
                .andExpect(jsonPath("$[0].name", Is.is("name10")))
                .andExpect(jsonPath("$[0].image").value(IsNull.nullValue()))
                .andExpect(jsonPath("$[0].lastMessage", Is.is("user 110 LAST message for tests")));
    }

    @Test
    @Sql(value = {"/script/TestChatResourceController/testUpdateImageGroupChat/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testUpdateImageGroupChat/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testUpdateImageGroupChat() throws Exception {
        String token = getToken("email0@domain.com", "password");

        //Successfully test's
        Long chatId = 100l;
        String newImage="http://images.com/newimage.jpeg";
        this.mvc.perform(patch("/api/user/chat/" + chatId + "/group/image")
                .header("Authorization", "Bearer " + token)
                .content(newImage)
                .contentType(MediaType.TEXT_PLAIN_VALUE));
        GroupChat groupChat = em.createQuery("select gc from GroupChat gc where gc.chat.id = :chatId", GroupChat.class)
                .setParameter("chatId", chatId)
                .getSingleResult();
        Assertions.assertTrue(groupChat.getImageLink().equals(newImage));

        //Bad group chat's id request
        chatId = 201l;
        this.mvc.perform(patch("/api/user/chat/" + chatId + "/group/image")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isBadRequest());
    }

    /*@Test
    @Sql(value = {"/script/TestChatResourceController/testDeleteChat/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testDeleteChat/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testDeleteChat() throws Exception {
        String token = getToken("email1@domain.com", "password");

        //        Successfully test's for group chat
        Long chatId = 100l;
        this.mvc.perform(delete("/api/user/chat/" + chatId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("Чат удалён"));

         List<GroupChat> groupChats = em.createQuery("select gc from GroupChat gc where gc.id = :id")
                .setParameter("id", chatId)
                .getResultList();
        Assertions.assertTrue(groupChats.isEmpty());

         //        Successfully test's for single chat
        chatId = 102l;
        this.mvc.perform(delete("/api/user/chat/" + chatId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string("Чат удалён"));

         List<SingleChat> singleChats = em.createQuery("select sc from SingleChat sc where sc.id = :id")
                .setParameter("id", chatId)
                .getResultList();
        Assertions.assertTrue(singleChats.isEmpty());

        //Wrong id test
        chatId = 1000l;
        this.mvc.perform(delete("/api/user/chat/" + chatId)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());
    }*/

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestChatResourceController/testGetGroupChat/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestChatResourceController/testGetGroupChat/After.sql"})
    })
    public void testGetGroupChat() throws Exception {
        String token = getToken("100@gmail.com", "100pwd");

        // Successful test
        this.mvc.perform(get("/api/user/chat/group").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // Method returned 2 GroupCHatDTO's with last messages for both of them
                .andExpect(jsonPath("$[0].id", Is.is(500)))
                .andExpect(jsonPath("$[0].chatName", Is.is("java1")))
                .andExpect(jsonPath("$[0].lastMessage", Is.is("testmessage1")))
                .andExpect(jsonPath("$[0].imageLink", Is.is("https://cdn-icons-png.flaticon.com/512/1144/1144761.png")))
                .andExpect(jsonPath("$[1].id", Is.is(501)))
                .andExpect(jsonPath("$[1].chatName", Is.is("java2")))
                .andExpect(jsonPath("$[1].lastMessage", Is.is("testmessage3")))
                .andExpect(jsonPath("$[1].imageLink", Is.is("https://cdn-icons-png.flaticon.com/512/1144/1144762.png")))
        // Method returned GroupChatDTO with null message
                .andExpect(jsonPath("$[2].id", Is.is(502)))
                .andExpect(jsonPath("$[2].chatName", Is.is("java3")))
                .andExpect(jsonPath("$[2].lastMessage", IsNull.nullValue()))
                .andExpect(jsonPath("$[2].imageLink", Is.is("https://cdn-icons-png.flaticon.com/512/1144/1144763.png")));
    }

    /*@Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestChatResourceController/testDeleteChatById/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestChatResourceController/testDeleteChatById/After.sql"})
    })
    public void testDeleteChatById() throws Exception {
        String token = getToken("100@gmail.com", "100pwd");
        // Successful test,method deleted User from GroupChat
        this.mvc.perform(delete("/api/user/chat/{id}",500).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());

        // Successful test,method deleted User from Singlechat
        this.mvc.perform(delete("/api/user/chat/{id}",502).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk());

        // Failed test, chat with such id does not exist
        this.mvc.perform(delete("/api/user/chat/{id}",5000).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isBadRequest());

        // Failed test, User is not in chat with such id
        this.mvc.perform(delete("/api/user/chat/{id}",503).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }*/


}
