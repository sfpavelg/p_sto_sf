package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestChatResourceController extends AbstractTestApi {

    @Test
    @Sql(value = {"/script/TestChatResourceController/testGetChatBySearch/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestChatResourceController/testGetChatBySearch/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetChatBySearch() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");
        //Тест на поиск чата по слову "JavaScript" - 1 чат
        this.mvc.perform(get("/api/user/chat/Script")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(101)))
                .andExpect(jsonPath("$.[0].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:41:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов по слову "Java" - должно выйти 2 чата
        this.mvc.perform(get("/api/user/chat/Java")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 0")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:40:33.176")))
                .andExpect(jsonPath("$.[1].id", Is.is(101)))
                .andExpect(jsonPath("$.[1].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.[1].lastMessage", Is.is("Hello 1")))
                .andExpect(jsonPath("$.[1].persistDateTimeLastMessage", Is.is("2023-06-18T13:41:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов с юзером
        this.mvc.perform(get("/api/user/chat/nickname1")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(104)))
                .andExpect(jsonPath("$.[0].name", Is.is("nickname1")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 100id")))
                .andExpect(jsonPath("$.[0].image", Is.is("https://img.com/1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:40:33.174")))
                .andExpect(status().isOk());
        // Тест на поиск чатов по запросу "nick" - результат 2 чата с юзерами
        this.mvc.perform(get("/api/user/chat/nick")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(104)))
                .andExpect(jsonPath("$.[0].name", Is.is("nickname1")))
                .andExpect(jsonPath("$.[0].lastMessage", Is.is("Hello 100id")))
                .andExpect(jsonPath("$.[0].image", Is.is("https://img.com/1")))
                .andExpect(jsonPath("$.[0].persistDateTimeLastMessage", Is.is("2023-06-18T13:40:33.174")))
                .andExpect(jsonPath("$.[1].id", Is.is(105)))
                .andExpect(jsonPath("$.[1].name", Is.is("nickname2")))
                .andExpect(jsonPath("$.[1].lastMessage", Is.is("Hello 100id, i am 102id")))
                .andExpect(jsonPath("$.[1].image", Is.is("https://img.com/2")))
                .andExpect(jsonPath("$.[1].persistDateTimeLastMessage", Is.is("2023-06-18T14:44:33.174")))
                .andExpect(status().isOk());

    }


}
