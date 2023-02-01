package com.javamentor.qa.platform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

//Пример написания тестового класса для контроллера
@Sql(value = {"/script/TestExampleController/testMethod/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/script/TestExampleController/testMethod/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TestExampleController extends AbstractTestApi {

    @Test
    public void testMethod() throws Exception {
        //correct request
        this.mvc.perform(get("/api/example/user/{id}", 100))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("email0@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name0")))
                .andExpect(jsonPath("$.persistDateTime", Is.is("2023-01-01T00:00:00")))
                .andExpect(jsonPath("$.isEnabled", Is.is(true)))
                .andExpect(jsonPath("$.isDeleted", Is.is(false)))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.linkSite", Is.is("http://site0.com")))
                .andExpect(jsonPath("$.linkGitHub", Is.is("http://github.com/0")))
                .andExpect(jsonPath("$.linkVk", Is.is("http://vk.com/0")))
                .andExpect(jsonPath("$.about", Is.is("about0")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink0.com")))
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-01T00:00:00")))
                .andExpect(jsonPath("$.nickname", Is.is("nickname0")))
                .andExpect(jsonPath("$.roleName", Is.is("ADMIN")));

        //invalid id
        this.mvc.perform(get("/api/example/user/{id}", 105))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("User with id = 105 not found")));
    }
}