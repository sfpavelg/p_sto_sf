package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestUserResourceController extends AbstractTestApi {

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserDtoController/testGetUserById/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserDtoController/testGetUserById/After.sql"})
    })
    public void testGetUserById() throws Exception {

//        Successfully test's
        this.mvc.perform(get("/api/user/{id}", 100))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("email1@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(6)));


        this.mvc.perform(get("/api/user/{id}", 101))
                .andExpect(jsonPath("$.id", Is.is(101)))
                .andExpect(jsonPath("$.email", Is.is("email2@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name2")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.reputation", Is.is(14)));

        this.mvc.perform(get("/api/user/{id}", 102))
                .andExpect(jsonPath("$.id", Is.is(102)))
                .andExpect(jsonPath("$.email", Is.is("email3@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name3")))
                .andExpect(jsonPath("$.city", Is.is("NY")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.reputation", Is.is(11)));

        this.mvc.perform(get("/api/user/{id}", 103))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(8)));

//        Successfully test's for user without reputation
        this.mvc.perform(get("/api/user/{id}", 104))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));


//        By not existing userId test
        this.mvc.perform(get("/api/user/{id}", 150))
                .andExpect(status().isNotFound());

    }

}
