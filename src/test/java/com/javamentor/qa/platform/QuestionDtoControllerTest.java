package com.javamentor.qa.platform;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import java.util.Random;

class QuestionDtoControllerTest extends AbstractTestApi {


    private int id = new Random().nextInt(10);


    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mvc.perform(
                        get("/api/user/question/{id}", id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}