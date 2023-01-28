package com.javamentor.qa.platform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;


class QuestionDtoControllerTest extends AbstractTestApi {


    @Test
    @Sql (value = {"/question/question-dto-data-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql (value = {"/question/question-dto-data-drop.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getQuestionDtoByIdTest() throws Exception {
        //success
        this.mvc.perform(get("/api/user/question/{id}", 1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.title", Is.is("title1")));
    }

//    @Test
//    @Sql (value = {"/question/question-dto-data-create.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//    public void shouldReturnDefaultMessage2() throws Exception {
//        this.mvc.perform(get("/api/user/question/{id}", 2))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
//    }
}