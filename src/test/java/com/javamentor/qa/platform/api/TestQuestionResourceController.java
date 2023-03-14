package com.javamentor.qa.platform.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;


class TestQuestionResourceController extends AbstractTestApi {

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetQuestionDtoById/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetQuestionDtoById/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getQuestionDtoByIdTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //success
        this.mvc.perform(get("/api/user/question/{id}", 100).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.title", Is.is("title1")))
                .andExpect(jsonPath("$.authorId", Is.is(100)))
                .andExpect(jsonPath("$.authorReputation", Is.is(6)))
                .andExpect(jsonPath("$.authorName", Is.is("name1")))
                .andExpect(jsonPath("$.authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.description", Is.is("description1")))
                .andExpect(jsonPath("$.title", Is.is("title1")))
                .andExpect(jsonPath("$.viewCount", Is.is(2)))
                .andExpect(jsonPath("$.countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.countValuable", Is.is(2)))
                .andExpect(jsonPath("$.persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.listTagDto[0].name", Is.is("name1")))
                .andExpect(jsonPath("$.listTagDto[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.listTagDto[1].id", Is.is(101)))
                .andExpect(jsonPath("$.listTagDto[1].name", Is.is("name2")))
                .andExpect(jsonPath("$.listTagDto[1].description", Is.is("description2")));

        //wrong id
        this.mvc.perform(get("/api/user/question/{id}", 111).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("QuestionDto with id = 111 not found")));

        //null results in DB (only possible fields like imageLink, rep, counts of answers and valuable)
        this.mvc.perform(get("/api/user/question/{id}", 104).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.title", Is.is("title5")))
                .andExpect(jsonPath("$.authorId", Is.is(104)))
                .andExpect(jsonPath("$.authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.authorName", Is.is("name5")))
                .andExpect(jsonPath("$.authorImage", IsNull.nullValue()))
                .andExpect(jsonPath("$.description", Is.is("description5")))
                .andExpect(jsonPath("$.title", Is.is("title5")))
                .andExpect(jsonPath("$.viewCount", Is.is(0)))
                .andExpect(jsonPath("$.countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.countValuable", Is.is(0)))
                .andExpect(jsonPath("$.persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")));

    }


    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testAddQuestion/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testAddQuestion/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addQuestionTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //Check that Question successfully added in DB if TagDto exist in DB
        List<TagDto> list1 = new ArrayList<>();
        list1.add(new TagDto(null, "name1", null));
        list1.add(new TagDto(null, "name2", null));
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle1", "testDescription1", list1)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.title", Is.is("testTitle1")))
                .andExpect(jsonPath("$.authorId", Is.is(100)))
                .andExpect(jsonPath("$.authorReputation", Is.is(6)))
                .andExpect(jsonPath("$.authorName", Is.is("name1")))
                .andExpect(jsonPath("$.authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.description", Is.is("testDescription1")))
                .andExpect(jsonPath("$.viewCount", Is.is(0)))
                .andExpect(jsonPath("$.countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.countValuable", Is.is(0)))
                .andExpect(jsonPath("$.listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.listTagDto[0].name", Is.is("name1")))
                .andExpect(jsonPath("$.listTagDto[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.listTagDto[1].id", Is.is(101)))
                .andExpect(jsonPath("$.listTagDto[1].name", Is.is("name2")))
                .andExpect(jsonPath("$.listTagDto[1].description", Is.is("description2")))
        ;

        //Check that Question and Tag successfully added in DB If TagDto doesn't exist in DB ,
        List<TagDto> list2 = new ArrayList<>();
        list2.add(new TagDto(null, "name100", null));
        list2.add(new TagDto(null, "name200", null));
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle2", "testDescription2", list2)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(2)))
                .andExpect(jsonPath("$.title", Is.is("testTitle2")))
                .andExpect(jsonPath("$.authorId", Is.is(100)))
                .andExpect(jsonPath("$.authorReputation", Is.is(6)))
                .andExpect(jsonPath("$.authorName", Is.is("name1")))
                .andExpect(jsonPath("$.authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.description", Is.is("testDescription2")))
                .andExpect(jsonPath("$.viewCount", Is.is(0)))
                .andExpect(jsonPath("$.countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.countValuable", Is.is(0)))
                .andExpect(jsonPath("$.listTagDto[0].id", Is.is(1)))
                .andExpect(jsonPath("$.listTagDto[0].name", Is.is("name100")))
                .andExpect(jsonPath("$.listTagDto[0].description", IsNull.nullValue()))
                .andExpect(jsonPath("$.listTagDto[1].id", Is.is(2)))
                .andExpect(jsonPath("$.listTagDto[1].name", Is.is("name200")))
                .andExpect(jsonPath("$.listTagDto[1].description", IsNull.nullValue()))
        ;

        //blank title
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto(" ", "testDescription1", list1)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Title can't be empty")))
        ;

        //null title
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto(null, "testDescription1", list1)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Title can't be empty")))
        ;

        //blank description
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle1", " ", list1)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Description can't be empty")))
        ;

        //null description
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle", null, list1)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Description can't be empty")))
        ;

        //Empty tags
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle1", "testDescription1", new ArrayList<>())))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("One tag at least must be chosen")))
        ;

        //Null tags
        this.mvc.perform(post("/api/user/question").header("Authorization", "Bearer " + token).content(this.objectMapper.writeValueAsString(
                                new QuestionCreateDto("testTitle1", "testDescription1", null)))
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("One tag at least must be chosen")))
        ;

    }


    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetAllQuestionDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetAllQuestionDto/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllQuestionDtoTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        this.mvc.perform(get("/api/user/question")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "1")
                        .param("ignoredTags", "100", "103", "106")
                        .param("trackedTags", "100", "101", "102"))
//  A question with a tag that is ignored and tracked is not shown
                .andDo(print())
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))

                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].title", Is.is("title2")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(101)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(4)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name2")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink2.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description2")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(1)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(101)))

                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].listTagDto[0].id", Is.is(102)))

                .andExpect(jsonPath("$.items[2].id", Is.is(105)))
                .andExpect(jsonPath("$.items[2].listTagDto[0].id", Is.is(105)))

                .andExpect(jsonPath("$.items[3].id", Is.is(108)))
                .andExpect(jsonPath("$.items[3].listTagDto[0].id", Is.is(101)))

                .andExpect(jsonPath("$.items[4].id", Is.is(109)))
                .andExpect(jsonPath("$.items[4].listTagDto[1].id", Is.is(105)))
                .andExpect(status().isOk());

//  Successful test on not existing page
        this.mvc.perform(get("/api/user/question")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "5"))
                .andDo(print())
                .andExpect(jsonPath("$.items").isEmpty())
                .andExpect(status().isOk());

//  Page with negative number can not be found
        this.mvc.perform(get("/api/user/question")
                        .header("Authorization", "Bearer " + token)
                        .param("currentPageNumber", "-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());

//  A question must have at least one tag, so questions without any tags are not shown
        this.mvc.perform(get("/api/user/question")
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.totalResultCount", Is.is(10)))
                .andExpect(status().isOk());

//  itemsOnPage test
        this.mvc.perform(get("/api/user/question")
                        .header("Authorization", "Bearer " + token)
                        .param("itemsOnPage", "2"))
                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(10)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))

                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[1].id", Is.is(101)))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController.testGetPageWithListQuestionDtoWithoutAnswer/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController.testGetPageWithListQuestionDtoWithoutAnswer/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getPageWithListQuestionDtoWithoutAnswerTest() throws Exception {
        String token = getToken("5@gmail.com", "5pwd");
        //Test 1. Positive. The request parameters are passed page, items, trackedTag, ignoredTag
        this.mvc.perform(get("/api/user/question/noAnswer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "2")
                        .param("items", "2")
                        .param("trackedTag", "101,102")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(3)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(105)))
                .andExpect(jsonPath("$.items[0].title", Is.is("question user id 105")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(105)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(5)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("superfullname4")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("https://img.com/4")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Asked by user id 105")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(1)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-03-11T17:18:55.740637")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-03-11T17:18:55.740637")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].description", Is.is("JavaScript")));

        //Test 2. Positive. The request parameters are passed page, items, trackedTag
        this.mvc.perform(get("/api/user/question/noAnswer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "3")
                        .param("items", "2")
                        .param("trackedTag", "101,102")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(3)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(105)))
                .andExpect(jsonPath("$.items[0].title", Is.is("question user id 105")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(105)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(5)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("superfullname4")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("https://img.com/4")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Asked by user id 105")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(1)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-03-11T17:18:55.740637")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-03-11T17:18:55.740637")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].name", Is.is("JavaScript")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].description", Is.is("JavaScript")));

        //Test 3. Positive. The request parameters are passed page, ignoredTag
        this.mvc.perform(get("/api/user/question/noAnswer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(3)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(104)))
                .andExpect(jsonPath("$.items[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[1].listTagDto.size()", Is.is(2)))
                .andExpect(jsonPath("$.items[2].id", Is.is(105)))
                .andExpect(jsonPath("$.items[2].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[2].listTagDto.size()", Is.is(1)));

        //Test 4. Negative test. Incorrect page and correct ignoredTag were passed
        this.mvc.perform(get("/api/user/question/noAnswer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().is4xxClientError());

        //Test 5. Negative test. The correct page and incorrect items were passed
        this.mvc.perform(get("/api/user/question/noAnswer")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("items", "0")
                )
                .andExpect(status().is4xxClientError());
    }

}