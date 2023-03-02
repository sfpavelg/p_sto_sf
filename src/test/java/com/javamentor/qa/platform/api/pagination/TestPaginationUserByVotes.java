package com.javamentor.qa.platform.api.pagination;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.user.UserDto;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Тест для пагинации пользователей отсортированных по голосам
 * @author Шапедько Андрей
 * @since 02/03/2023
 */
public class TestPaginationUserByVotes extends AbstractTestApi {

    @Test
    @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
            value = {"/script/TestUserResourceController/pagination/testGetUsersSortedByVote/Before.sql"})
    @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
            value = {"/script/TestUserResourceController/pagination/testGetUsersSortedByVote/After.sql"})
    public void test() throws Exception {

        // список загруженных пользователей
        var dto0 = new UserDto(100L, "0@gmail.com", "name1", "Moscow", "http://imagelink1.com", 0);
        var dto1 = new UserDto(101L, "email2@domain.com", "name2", "St. Petersburg", "http://imagelink2.com", 0);
        var dto2 = new UserDto(102L, "email3@domain.com", "name3", "Kazan", null, 0);
        var dto3 = new UserDto(103L, "email4@domain.com", "name4", "Ekaterinburg", "http://imagelink4.com", 0);
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

        // пользователь не авторизован (отсутствует JWT)
        this.mvc.perform(get("/api/user/vote"))
                .andDo(print())
                .andExpect(status().is4xxClientError());


        // на странице должны быть все 5 элементов
        this.mvc.perform(get("/api/user/vote").header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(0)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(0)))
                .andExpect(jsonPath("$.items.size()", Is.is(5)))
                .andExpect(jsonPath("$.items[0].id", Is.is(dto3.getId().intValue())))
                .andExpect(jsonPath("$.items[1].id", Is.is(dto2.getId().intValue())))
                .andExpect(jsonPath("$.items[2].id", Is.is(dto4.getId().intValue())))
                .andExpect(jsonPath("$.items[3].id", Is.is(dto1.getId().intValue())))
                .andExpect(jsonPath("$.items[4].id", Is.is(dto0.getId().intValue())));

        // на странице 1 элемент, выбранная страница - последняя - dto0 -
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "1")
                        .param("page", "4"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(4)))
                .andExpect(jsonPath("$.items[0].id", Is.is(dto0.getId().intValue())));

        // на странице 2 элемента, выбранная страница - 2 - dto4, dto1
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "2")
                        .param("page", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.items.size()", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(dto4.getId().intValue())))
                .andExpect(jsonPath("$.items[1].id", Is.is(dto1.getId().intValue())));

        // выбранная страница за пределами общего количества страниц - ошибка 404
        this.mvc.perform(get("/api/user/vote")
                        .header("Authorization", "Bearer " + token)
                        .param("items", "5")
                        .param("page", "1"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
