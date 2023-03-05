package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.security.jwt.AuthenticationRequest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        String jwt = jsonParser.parseMap(this.mvc.perform(post("/api/auth/token")
                                .content(objectMapper.valueToTree(new AuthenticationRequest("email5@domain.com",
                                        "password")).toString())
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().is2xxSuccessful())
                        .andReturn().getResponse().getContentAsString())
                .get("token").toString();

//        Successfully test's
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("email1@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(6)));


        this.mvc.perform(get("/api/user/{id}", 101)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(jsonPath("$.id", Is.is(101)))
                .andExpect(jsonPath("$.email", Is.is("email2@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name2")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.reputation", Is.is(14)));

        this.mvc.perform(get("/api/user/{id}", 102)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(jsonPath("$.id", Is.is(102)))
                .andExpect(jsonPath("$.email", Is.is("email3@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name3")))
                .andExpect(jsonPath("$.city", Is.is("NY")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.reputation", Is.is(11)));

        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(8)));

//        Successfully test's for user without reputation
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));


//        By not existing userId test
        this.mvc.perform(get("/api/user/{id}", 150)
                        .header("Authorization", "Bearer " + jwt))
                .andExpect(status().isNotFound());

    }


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController.testGetUsersByPersistDateAndTime/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController.testGetUsersByPersistDateAndTime/After.sql"})
    })
    public void testGetAllUsersByPersistDateAndTime() throws Exception {

        String token = getToken("email5@domain.com", "password");

//  Successful test (The newest user shown first). Works with null reg date. User with null reg date shown first
        this.mvc.perform(get("/api/user/new")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "0"))
                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(0)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))

                .andExpect(jsonPath("$.items[0].id", Is.is(104)))
                .andExpect(jsonPath("$.items[0].email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("name5")))
                .andExpect(jsonPath("$.items[0].city", Is.is("moscow")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(0)))

                .andExpect(jsonPath("$.items[1].id", Is.is(103)))
                .andExpect(jsonPath("$.items[1].email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("name4")))
                .andExpect(jsonPath("$.items[1].city", Is.is("spb")))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(8)))

                .andExpect(jsonPath("$.items[2].id", Is.is(102)))
                .andExpect(jsonPath("$.items[2].email", Is.is("email3@domain.com")))
                .andExpect(jsonPath("$.items[2].fullName", Is.is("name3")))
                .andExpect(jsonPath("$.items[2].city", Is.is("NY")))
                .andExpect(jsonPath("$.items[2].imageLink", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.items[2].reputation", Is.is(11)))

                .andExpect(jsonPath("$.items[3].id", Is.is(101)))
                .andExpect(jsonPath("$.items[3].email", Is.is("email2@domain.com")))
                .andExpect(jsonPath("$.items[3].fullName", Is.is("name2")))
                .andExpect(jsonPath("$.items[3].city", Is.is("spb")))
                .andExpect(jsonPath("$.items[3].imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.items[3].reputation", Is.is(14)))

                .andExpect(jsonPath("$.items[4].id", Is.is(100)))
                .andExpect(jsonPath("$.items[4].email", Is.is("email1@domain.com")))
                .andExpect(jsonPath("$.items[4].fullName", Is.is("name1")))
                .andExpect(jsonPath("$.items[4].city", Is.is("moscow")))
                .andExpect(jsonPath("$.items[4].imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[4].reputation", Is.is(6)));

//  Successful test on not existing page
        this.mvc.perform(get("/api/user/new")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "5"))
                .andDo(print())
                .andExpect(jsonPath("$.items").isEmpty())
                .andExpect(status().isOk());

//  Page with negative number can not be found
        this.mvc.perform(get("/api/user/new")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testChangeUserPassword/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testChangeUserPassword/After.sql"})
    })
    public void testChangeUserPassword() throws Exception {

        String userToken = getToken("4@gmail.com", "4pwd");

        // Empty password
        this.mvc.perform(patch("/api/user/changePassword").header("Authorization", "Bearer " + userToken)
                        .content("").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string(containsString("The password must not be empty")));

        // Short password
        this.mvc.perform(patch("/api/user/changePassword").header("Authorization", "Bearer " + userToken)
                        .content("1pwd").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The password must be at least 6 characters long"));
//
        // Password of numbers
        this.mvc.perform(patch("/api/user/changePassword").header("Authorization", "Bearer " + userToken)
                        .content("123456").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().string("The password should not consist only of numbers"));

        // Correct password
        this.mvc.perform(patch("/api/user/changePassword").header("Authorization", "Bearer " + userToken)
                        .content("pwd456").contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("Password changed successfully"));

    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController.testGetPageWithListUsersSortedByReputation/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController.testGetPageWithListUsersSortedByReputation/After.sql"})
    })
    public void testGetPageWithListUsersSortedByReputation() throws Exception {
        // Get token
        String jwt = getToken("5@gmail.com", "5pwd");
        // Positive Test. Output of the second page in order with two elements
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("items", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(25)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(50)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(103)))
                .andExpect(jsonPath("$.items[0].email", Is.is("super2@gmail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("superfullname2")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city2")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("https://img.com/2")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(3)))
                .andExpect(jsonPath("$.items[1].id", Is.is(104)))
                .andExpect(jsonPath("$.items[1].email", Is.is("super3@gmail.com")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("superfullname3")))
                .andExpect(jsonPath("$.items[1].city", Is.is("city3")))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is("https://img.com/3")))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(4)));

        // Positive Test. Output of the second page in order without specifying the optional parameter "items"
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(50)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(111)))
                .andExpect(jsonPath("$.items[0].email", Is.is("5@gmail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("fullname5")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city5")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("https://img.com/5")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(11)));

        // Negative Test. A negative page number is set incorrect
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "-1")
                        .param("items", "1")
                )
                .andExpect(status().isBadRequest());

        // Negative Test. GET request parameters are not set
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().is4xxClientError());
    }
}
