package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class TestUserResourceController extends AbstractTestApi {

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetUserById/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetUserById/After.sql"})
    })
    public void testGetUserById() throws Exception {
        String token = getToken("email5@domain.com", "password");

//        Successfully test's
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("email1@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(6)));


        this.mvc.perform(get("/api/user/{id}", 101)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(101)))
                .andExpect(jsonPath("$.email", Is.is("email2@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name2")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.reputation", Is.is(14)));

        this.mvc.perform(get("/api/user/{id}", 102)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(102)))
                .andExpect(jsonPath("$.email", Is.is("email3@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name3")))
                .andExpect(jsonPath("$.city", Is.is("NY")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.reputation", Is.is(11)));

        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("spb")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(8)));

//        Successfully test's for user without reputation
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));


//        By not existing userId test
        this.mvc.perform(get("/api/user/{id}", 150)
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNotFound());

    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetUsersByPersistDateAndTime/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetUsersByPersistDateAndTime/After.sql"})
    })
    public void testGetAllUsersByPersistDateAndTime() throws Exception {
        String token = getToken("email5@domain.com", "password");

//  Successful test (The newest user shown first). Works with null reg date. User with null reg date shown first
        this.mvc.perform(get("/api/user/new")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "1"))
                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
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
                    value = {"/script/TestUserResourceController/testGetUsersSortedByVote/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetUsersSortedByVote/After.sql"})}
    )
    public void testGetUsersSortedByVote() throws Exception {

        // список загруженных пользователей
        var dto0 = new UserDto(100L, "0@gmail.com", "name1", "Moscow", "http://imagelink1.com", 6);
        var dto1 = new UserDto(101L, "email2@domain.com", "name2", "St. Petersburg", "http://imagelink2.com", 14);
        var dto2 = new UserDto(102L, "email3@domain.com", "name3", "Kazan", null, 11);
        var dto3 = new UserDto(103L, "email4@domain.com", "name4", "Ekaterinburg", "http://imagelink4.com", 8);
        var dto4 = new UserDto(104L, "email5@domain.com", "name5", "Samara", "http://imagelink5.com", 0);

        /*
         * В скрипте Before были добавлены VoteQuestion и VoteAnswer таким образом,
         * что голоса распределились след образом:
         * (userId : UP_VOTE's : DOWN_VOTE's : СУММА)
         * 103     :     5     :      0      :   5
         * 102     :     3     :      1      :   2
         * 104     :     1     :      0      :   1
         * 101     :     1     :      1      :   0
         * 100     :     0     :      2      :  -2
         *
         * */

        String token = getToken("0@gmail.com", "0pwd");

        // user not authorized (JWT missed)
        this.mvc.perform(get("/api/user/vote"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


        // на странице должны быть все 5 элементов
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items.size()", Is.is(5)))

                .andExpect(jsonPath("$.items[0].id", Is.is(dto3.getId().intValue())))
                .andExpect(jsonPath("$.items[0].email", Is.is(dto3.getEmail())))
                .andExpect(jsonPath("$.items[0].fullName", Is.is(dto3.getFullName())))
                .andExpect(jsonPath("$.items[0].city", Is.is(dto3.getCity())))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is(dto3.getImageLink())))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(dto3.getReputation())))

                .andExpect(jsonPath("$.items[1].id", Is.is(dto2.getId().intValue())))
                .andExpect(jsonPath("$.items[1].email", Is.is(dto2.getEmail())))
                .andExpect(jsonPath("$.items[1].fullName", Is.is(dto2.getFullName())))
                .andExpect(jsonPath("$.items[1].city", Is.is(dto2.getCity())))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is(dto2.getImageLink())))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(dto2.getReputation())))

                .andExpect(jsonPath("$.items[2].id", Is.is(dto4.getId().intValue())))
                .andExpect(jsonPath("$.items[2].email", Is.is(dto4.getEmail())))
                .andExpect(jsonPath("$.items[2].fullName", Is.is(dto4.getFullName())))
                .andExpect(jsonPath("$.items[2].city", Is.is(dto4.getCity())))
                .andExpect(jsonPath("$.items[2].imageLink", Is.is(dto4.getImageLink())))
                .andExpect(jsonPath("$.items[2].reputation", Is.is(dto4.getReputation())))

                .andExpect(jsonPath("$.items[3].id", Is.is(dto1.getId().intValue())))
                .andExpect(jsonPath("$.items[3].email", Is.is(dto1.getEmail())))
                .andExpect(jsonPath("$.items[3].fullName", Is.is(dto1.getFullName())))
                .andExpect(jsonPath("$.items[3].city", Is.is(dto1.getCity())))
                .andExpect(jsonPath("$.items[3].imageLink", Is.is(dto1.getImageLink())))
                .andExpect(jsonPath("$.items[3].reputation", Is.is(dto1.getReputation())))

                .andExpect(jsonPath("$.items[4].id", Is.is(dto0.getId().intValue())))
                .andExpect(jsonPath("$.items[4].email", Is.is(dto0.getEmail())))
                .andExpect(jsonPath("$.items[4].fullName", Is.is(dto0.getFullName())))
                .andExpect(jsonPath("$.items[4].city", Is.is(dto0.getCity())))
                .andExpect(jsonPath("$.items[4].imageLink", Is.is(dto0.getImageLink())))
                .andExpect(jsonPath("$.items[4].reputation", Is.is(dto0.getReputation())));

        // на странице 1 элемент, выбранная страница - последняя - dto0 -
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "1")
                        .param("page", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(5)))

                .andExpect(jsonPath("$.items[0].id", Is.is(dto0.getId().intValue())))
                .andExpect(jsonPath("$.items[0].email", Is.is(dto0.getEmail())))
                .andExpect(jsonPath("$.items[0].fullName", Is.is(dto0.getFullName())))
                .andExpect(jsonPath("$.items[0].city", Is.is(dto0.getCity())))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is(dto0.getImageLink())))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(dto0.getReputation())));

        // на странице 2 элемента, выбранная страница - 2 - dto4, dto1
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "2")
                        .param("page", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.items.size()", Is.is(2)))

                .andExpect(jsonPath("$.items[0].id", Is.is(dto4.getId().intValue())))
                .andExpect(jsonPath("$.items[0].email", Is.is(dto4.getEmail())))
                .andExpect(jsonPath("$.items[0].fullName", Is.is(dto4.getFullName())))
                .andExpect(jsonPath("$.items[0].city", Is.is(dto4.getCity())))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is(dto4.getImageLink())))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(dto4.getReputation())))

                .andExpect(jsonPath("$.items[1].id", Is.is(dto1.getId().intValue())))
                .andExpect(jsonPath("$.items[1].email", Is.is(dto1.getEmail())))
                .andExpect(jsonPath("$.items[1].fullName", Is.is(dto1.getFullName())))
                .andExpect(jsonPath("$.items[1].city", Is.is(dto1.getCity())))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is(dto1.getImageLink())))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(dto1.getReputation())));


        // выбранная страница за пределами общего количества страниц - пустой массив
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("page", "3"))
                .andDo(print())
                .andExpect(jsonPath("$.items.size()", Is.is(0)));

        // тест PageDtoService, чтобы totalPage не был равен 0
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetPageWithListUsersSortedByReputation/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetPageWithListUsersSortedByReputation/After.sql"})
    })
    public void testGetPageWithListUsersSortedByReputation() throws Exception {
        String jwt = getToken("5@gmail.com", "5pwd");

        // Positive Test. Output of the second page in order with two elements
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "2")
                        .param("items", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(25)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(50)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(148)))
                .andExpect(jsonPath("$.items[0].email", Is.is("42@gmail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("fullname42")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city42")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("https://img.com/42")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(48)))

                .andExpect(jsonPath("$.items[1].id", Is.is(147)))
                .andExpect(jsonPath("$.items[1].email", Is.is("41@gmail.com")))
                .andExpect(jsonPath("$.items[1].fullName", Is.is("fullname41")))
                .andExpect(jsonPath("$.items[1].city", Is.is("city41")))
                .andExpect(jsonPath("$.items[1].imageLink", Is.is("https://img.com/41")))
                .andExpect(jsonPath("$.items[1].reputation", Is.is(47)));

        // Positive Test. Output of the second page in order without specifying the optional parameter "items"
        this.mvc.perform(get("/api/user/reputation")
                        .header("Authorization", "Bearer " + jwt)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "2")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(50)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(140)))
                .andExpect(jsonPath("$.items[0].email", Is.is("34@gmail.com")))
                .andExpect(jsonPath("$.items[0].fullName", Is.is("fullname34")))
                .andExpect(jsonPath("$.items[0].city", Is.is("city34")))
                .andExpect(jsonPath("$.items[0].imageLink", Is.is("https://img.com/34")))
                .andExpect(jsonPath("$.items[0].reputation", Is.is(40)));

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

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetPageWithListTop10UsersAnswers/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestUserResourceController/testGetPageWithListTop10UsersAnswers/After.sql"})
    })
    public void testGetPageWithListTop10UsersAnswer() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");


        //  Top 10 users ordered by count answer, then rating of answer and then ordered by reputation
        this.mvc.perform(get("/api/user/top10UsersAnswers")
                        .header("Authorization", "Bearer " + token))

                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.[0].id", Is.is(101)))
                .andExpect(jsonPath("$.[0].email", Is.is("super1@gmail.com")))
                .andExpect(jsonPath("$.[0].fullName", Is.is("superfullname1")))
                .andExpect(jsonPath("$.[0].city", Is.is("city1")))
                .andExpect(jsonPath("$.[0].imageLink", Is.is("https://img.com/1")))
                .andExpect(jsonPath("$.[0].reputation", Is.is(1)))

                .andExpect(jsonPath("$.[1].id", Is.is(100)))
                .andExpect(jsonPath("$.[1].email", Is.is("super0@gmail.com")))
                .andExpect(jsonPath("$.[1].reputation", Is.is(0)))

                .andExpect(jsonPath("$.[2].id", Is.is(114)))
                .andExpect(jsonPath("$.[2].email", Is.is("9@gmail.com")))
                .andExpect(jsonPath("$.[2].reputation", Is.is(14)))

                .andExpect(jsonPath("$.[3].id", Is.is(103)))
                .andExpect(jsonPath("$.[3].email", Is.is("super3@gmail.com")))
                .andExpect(jsonPath("$.[3].reputation", Is.is(3)))

                .andExpect(jsonPath("$.[4].id", Is.is(102)))
                .andExpect(jsonPath("$.[4].email", Is.is("super2@gmail.com")))
                .andExpect(jsonPath("$.[4].reputation", Is.is(2)))

                .andExpect(jsonPath("$.[5].id", Is.is(112)))
                .andExpect(jsonPath("$.[5].email", Is.is("7@gmail.com")))
                .andExpect(jsonPath("$.[5].reputation", Is.is(12)))

                .andExpect(jsonPath("$.[6].id", Is.is(111)))
                .andExpect(jsonPath("$.[6].email", Is.is("6@gmail.com")))
                .andExpect(jsonPath("$.[6].reputation", Is.is(11)))

                .andExpect(jsonPath("$.[7].id", Is.is(109)))
                .andExpect(jsonPath("$.[7].email", Is.is("4@gmail.com")))
                .andExpect(jsonPath("$.[7].reputation", Is.is(9)))

                .andExpect(jsonPath("$.[8].id", Is.is(108)))
                .andExpect(jsonPath("$.[8].email", Is.is("3@gmail.com")))
                .andExpect(jsonPath("$.[8].reputation", Is.is(8)))

                .andExpect(jsonPath("$.[9].id", Is.is(106)))
                .andExpect(jsonPath("$.[9].email", Is.is("1@gmail.com")))
                .andExpect(jsonPath("$.[9].reputation", Is.is(6)));

    }

    @Test
    @Sql(value = {"/script/TestUserResourceController/testGetBookmarksDtoByUserId/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestUserResourceController/testGetBookmarksDtoByUserId/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetBookmarksDtoByUserId() throws Exception {
        String token1 = getToken("1000@gmail.com", "44pwd");
        String token2 = getToken("1001@gmail.com", "44pwd");
        String token3 = getToken("1002@gmail.com", "44pwd");
        String token4 = getToken("1003@gmail.com", "44pwd");

        //success with 0 bookmarks
        this.mvc.perform(get("/api/user/profile/bookmarks").header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(0)));

        //success with 1 bookmarks
        this.mvc.perform(get("/api/user/profile/bookmarks").header("Authorization", "Bearer " + token2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(1)))
                .andExpect(jsonPath("$.[0].questionId", Is.is(1000)))
                .andExpect(jsonPath("$.[0].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[0].tagDtoList.[0].id", Is.is(1000)))
                .andExpect(jsonPath("$.[0].tagDtoList.[1].id", Is.is(1001)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(2)))
                .andExpect(jsonPath("$.[0].countVote", Is.is(0)))
                .andExpect(jsonPath("$.[0].countView", Is.is(2)));

        //success with 2 bookmarks
        this.mvc.perform(get("/api/user/profile/bookmarks").header("Authorization", "Bearer " + token3))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(2)))
                .andExpect(jsonPath("$.[0].questionId", Is.is(1000)))
                .andExpect(jsonPath("$.[0].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[0].tagDtoList.[0].id", Is.is(1000)))
                .andExpect(jsonPath("$.[0].tagDtoList.[1].id", Is.is(1001)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(2)))
                .andExpect(jsonPath("$.[0].countVote", Is.is(0)))
                .andExpect(jsonPath("$.[0].countView", Is.is(2)))
                .andExpect(jsonPath("$.[1].questionId", Is.is(1001)))
                .andExpect(jsonPath("$.[1].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[1].tagDtoList.[0].id", Is.is(1002)))
                .andExpect(jsonPath("$.[1].tagDtoList.[1].id", Is.is(1003)))
                .andExpect(jsonPath("$.[1].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.[1].countVote", Is.is(1)))
                .andExpect(jsonPath("$.[1].countView", Is.is(1)));

        //success with 3 bookmarks
        this.mvc.perform(get("/api/user/profile/bookmarks").header("Authorization", "Bearer " + token4))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()", Is.is(3)))
                .andExpect(jsonPath("$.[0].questionId", Is.is(1002)))
                .andExpect(jsonPath("$.[0].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[0].tagDtoList.[0].id", Is.is(1004)))
                .andExpect(jsonPath("$.[0].tagDtoList.[1].id", Is.is(1005)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.[0].countVote", Is.is(2)))
                .andExpect(jsonPath("$.[0].countView", Is.is(1)))
                .andExpect(jsonPath("$.[1].questionId", Is.is(1003)))
                .andExpect(jsonPath("$.[1].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[1].tagDtoList.[0].id", Is.is(1006)))
                .andExpect(jsonPath("$.[1].tagDtoList.[1].id", Is.is(1007)))
                .andExpect(jsonPath("$.[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.[1].countVote", Is.is(0)))
                .andExpect(jsonPath("$.[1].countView", Is.is(1)))
                .andExpect(jsonPath("$.[2].questionId", Is.is(1004)))
                .andExpect(jsonPath("$.[2].tagDtoList.size()", Is.is(2)))
                .andExpect(jsonPath("$.[2].tagDtoList.[0].id", Is.is(1008)))
                .andExpect(jsonPath("$.[2].tagDtoList.[1].id", Is.is(1009)))
                .andExpect(jsonPath("$.[2].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.[2].countVote", Is.is(0)))
                .andExpect(jsonPath("$.[2].countView", Is.is(0)));
    }
}
