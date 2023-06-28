package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TestAnswerResourceController extends AbstractTestApi {


    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/TestGetMostPopularAnswersByVotes/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestAnswerResourceController/TestGetMostPopularAnswersByVotes/After.sql"})}
    )
    public void testGetMostPopularAnswersByVotes() throws Exception {

        /*
         * В скрипте Before были добавлены VoteAnswer таким образом,
         * что голоса распределились след образом:
         * (answerId : UP_VOTE's : DOWN_VOTE's : СУММА)
         * 104     :     1     :      0      :   1
         * 103     :     5     :      0      :   5
         * 102     :     3     :      1      :   2
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
        this.mvc.perform(get("/api/user/answer/popular")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("currentPage", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items.size()", Is.is(5)))

                .andExpect(jsonPath("$.items[0].id", Is.is(103)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(5)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(2)))
                .andExpect(jsonPath("$.items[2].countValuable", Is.is(1)))
                .andExpect(jsonPath("$.items[3].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countValuable", Is.is(-2)));

        // на странице 1 элемент, выбранная страница - последняя
        this.mvc.perform(get("/api/user/answer/popular")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "1")
                        .param("currentPage", "5"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(5)))

                .andExpect(jsonPath("$.items[0].countValuable", Is.is(-2)));

        // на странице 2 элемента, выбранная страница - 2
        this.mvc.perform(get("/api/user/answer/popular")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "2")
                        .param("currentPage", "2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.items.size()", Is.is(2)))

                .andExpect(jsonPath("$.items[0].countValuable", Is.is(1)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(0)));

        // выбранная страница за пределами общего количества страниц - пустой массив
        this.mvc.perform(get("/api/user/answer/popular")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("currentPage", "3"))
                .andDo(print())
                .andExpect(jsonPath("$.items.size()", Is.is(0)));

        // тест PageDtoService, чтобы totalPage не был равен 0
        this.mvc.perform(get("/api/user/answer/popular")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)));
    }
}
