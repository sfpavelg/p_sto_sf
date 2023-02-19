package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.security.jwt.AuthenticationRequest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TestTagController extends AbstractTestApi {


    @Test
    @Sql(value = {"/script/TestTagController/testGetAllUserIgnoredTag/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestTagController/testGetAllUserIgnoredTag/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetAllUserIgnoredTag() throws Exception {

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String jwt = jsonParser.parseMap(this.mvc.perform(post("/api/auth/token")
                                .content(objectMapper.valueToTree(new AuthenticationRequest("4@gmail.com",
                                        "4pwd")).toString())
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is2xxSuccessful())
                        .andReturn().getResponse().getContentAsString())
                .get("token").toString();


        this.mvc.perform(get("/api/user/tag/ignored")
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].name", Is.is("tag1")))
                .andExpect(jsonPath("$.[1].id", Is.is(101)))
                .andExpect(jsonPath("$.[1].name", Is.is("tag2")))
                .andExpect(jsonPath("$.[2].id", Is.is(103)))
                .andExpect(jsonPath("$.[2].name", Is.is("tag4")));

    }
}
