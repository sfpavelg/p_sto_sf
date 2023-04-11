package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.AuthenticationRequest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;


public class TestAuthenticationResourceController extends AbstractTestApi {

    @Test
    @Sql(value = {"/script/TestAuthenticationResourceController/testAuthentication/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestAuthenticationResourceController/testAuthentication/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAuthentication() throws Exception {
        // correct request
        this.mvc.perform(post("http://localhost:8091/api/auth/token")
                .content(objectMapper.valueToTree(new AuthenticationRequest("5@gmail.com", "5pwd")).toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token", Is.is("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI1QGdtYWlsLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdfQ.-PanoBuwYEPiHtUWgV9poBvXDBcFS7QvrzOb5KohqTyKH-JddXRMDHATskjAqn9geMaQkiQAchH_NKTIzWT2tA")));
        // incorrect request
        this.mvc.perform(post("http://localhost:8091/api/auth/token")
                .content(objectMapper.valueToTree(new AuthenticationRequest("user@gmail.com", "pwd")).toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
