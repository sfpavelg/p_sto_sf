package com.javamentor.qa.platform;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

public class TagResourceControllerTest extends AbstractTestApi {

    @Test
    @Sql(value = {"/script/tag/getRelatedTagsDtoListTest/related-tags-dto-data-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/tag/getRelatedTagsDtoListTest/related-tags-dto-data-drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getRelatedTagsDtoListTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");
        //success getting TOP-10 Tags from 15 in DB (ordered by countQuestion)
        this.mvc.perform(get("/api/user/tag/related").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].title", Is.is("name1")))
                .andExpect(jsonPath("$.[0].countQuestion", Is.is(4)))
                .andExpect(jsonPath("$.[1].id", Is.is(101)))
                .andExpect(jsonPath("$.[1].title", Is.is("name2")))
                .andExpect(jsonPath("$.[1].countQuestion", Is.is(3)))
                .andExpect(jsonPath("$.[2].id", Is.is(102)))
                .andExpect(jsonPath("$.[2].title", Is.is("name3")))
                .andExpect(jsonPath("$.[2].countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.[3].id", Is.is(103)))
                .andExpect(jsonPath("$.[3].title", Is.is("name4")))
                .andExpect(jsonPath("$.[3].countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.[4].id", Is.is(104)))
                .andExpect(jsonPath("$.[4].title", Is.is("name5")))
                .andExpect(jsonPath("$.[4].countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.[5].id", Is.is(105)))
                .andExpect(jsonPath("$.[5].title", Is.is("name6")))
                .andExpect(jsonPath("$.[5].countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.[6].id", Is.is(106)))
                .andExpect(jsonPath("$.[6].title", Is.is("name7")))
                .andExpect(jsonPath("$.[6].countQuestion", Is.is(2)))
                .andExpect(jsonPath("$.[7].id", Is.is(107)))
                .andExpect(jsonPath("$.[7].title", Is.is("name8")))
                .andExpect(jsonPath("$.[7].countQuestion", Is.is(1)))
                .andExpect(jsonPath("$.[8].id", Is.is(108)))
                .andExpect(jsonPath("$.[8].title", Is.is("name9")))
                .andExpect(jsonPath("$.[8].countQuestion", Is.is(1)))
                .andExpect(jsonPath("$.[9].id", Is.is(109)))
                .andExpect(jsonPath("$.[9].title", Is.is("name10")))
                .andExpect(jsonPath("$.[9].countQuestion", Is.is(1)))
        ;
    }

    @Test
    @Sql(value = {"/script/tag/addIgnoredTag/add-ignored-tag-data-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/tag/addIgnoredTag/add-ignored-tag-data-drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addIgnoredTag() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //success adding ignored tag and returning TagDto
        this.mvc.perform(post("/api/user/tag/{id}/ignored", 100).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.name", Is.is("name1")))
                .andExpect(jsonPath("$.description", Is.is("description1")));

        //wrong id
        this.mvc.perform(post("/api/user/tag/{id}/ignored", 1000).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("Tag with id = 1000 not found")));


    }

    @Test
    @Sql(value = {"/script/tag/addTrackedTag/add-tracked-tag-data-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/tag/addTrackedTag/add-tracked-tag-data-drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addTrackedTag() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //success adding ignored tag and returning TagDto
        this.mvc.perform(post("/api/user/tag/{id}/tracked", 101).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(101)))
                .andExpect(jsonPath("$.name", Is.is("name2")))
                .andExpect(jsonPath("$.description", Is.is("description2")));

        //wrong id
        this.mvc.perform(post("/api/user/tag/{id}/tracked", 1000).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("Tag with id = 1000 not found")));


    }
}
