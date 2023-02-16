package com.javamentor.qa.platform;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public class AuthorizationUserForTestsUtil extends AbstractTestApi {
    private static final String AUTH_ENDPOINT = "http://localhost:8091/api/auth/token";

    public static HttpHeaders getHeadersWithToken(String email, String password, MockMvc mvc) throws Exception {
        String json = "{\"email\":\"" + email + "\",\"password\":\"" + password + "\"}";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(
                mvc.perform(post(AUTH_ENDPOINT).contentType(MediaType.APPLICATION_JSON)
                                .content(json)).andReturn().getResponse()
                        .getContentAsString().substring(10));
        return httpHeaders;
    }
}
