package com.javamentor.qa.platform.api;

import com.javamentor.qa.platform.AbstractTestApi;
import com.javamentor.qa.platform.models.dto.question.QuestionCreateDto;
import com.javamentor.qa.platform.models.dto.tag.TagDto;
import org.hamcrest.core.Is;
import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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
                .andExpect(jsonPath("$.countValuable", Is.is(0)))
                .andExpect(jsonPath("$.persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.isUserVote", Is.is("UP_VOTE")))
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
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.isUserVote", Is.is("DOWN_VOTE")));

        //success
        this.mvc.perform(get("/api/user/question/{id}", 103).header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.authorId", Is.is(103)))
                .andExpect(jsonPath("$.isUserVote", IsNull.nullValue()));
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
    @Sql(value = {"/script/TestQuestionResourceController/testVoteForQuestion/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/script/TestQuestionResourceController/testVoteForQuestion/After.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void voteForQuestionTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        // пользователь не авторизован (отсутствует JWT) - ошибка
        this.mvc.perform(post("/api/user/question/1/upVote"))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        // голосование за несуществующий вопрос - ошибка
        this.mvc.perform(post("/api/user/question/1/upVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", Is.is("Question with id 1 not found.")));

        // UpVote за 103 вопрос - за него никто еще не голосовал
        this.mvc.perform(post("/api/user/question/103/upVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(1)));
        // проверка репутации после голосования
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(10)));


        // DownVote за 103 вопрос - существующий UpVote меняется на DownVote
        this.mvc.perform(post("/api/user/question/103/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        // проверка репутации после голосования
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));


        // повторный DownVote за 103 вопрос - голос не должен дублироваться и результат должен остаться тем же
        this.mvc.perform(post("/api/user/question/103/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        // проверка репутации после голосования
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 103)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(103)))
                .andExpect(jsonPath("$.email", Is.is("email4@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name4")))
                .andExpect(jsonPath("$.city", Is.is("Ekaterinburg")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));

        // UpVote за 104 вопрос, за который уже проголосовали и изначально имеет рейтинг 15 = ( 2*10 - 1*(-5) )
        this.mvc.perform(post("/api/user/question/104/upVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(2)));
        // проверка репутации после голосования
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("Samara")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(25)));

        // DownVote за 104 вопрос
        this.mvc.perform(post("/api/user/question/104/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(0)));
        this.mvc.perform(get("/api/user/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.email", Is.is("email5@domain.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name5")))
                .andExpect(jsonPath("$.city", Is.is("Samara")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.reputation", Is.is(10)));

        // DownVote за 100 вопрос (сам себе поставил дизлайк на вопрос)
        this.mvc.perform(post("/api/user/question/100/downVote")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Is.is(-1)));
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name1")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.reputation", Is.is(-5)));

    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListQuestionDtoWithoutAnswer/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListQuestionDtoWithoutAnswer/After.sql"},
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

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetAllCommentDtoByQuestionId/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/script/TestQuestionResourceController/testGetAllCommentDtoByQuestionId/After.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getAllCommentDtoByQuestionIdTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //Test 1. Negative test. User not authorized (missing JWT) - error
        this.mvc.perform(get("/api/user/question/{id}/allComments", 104))
                .andDo(print())
                .andExpect(status().is4xxClientError());

        //Test 2. Positive. Authorized user
        this.mvc.perform(get("/api/user/{id}", 100)
                        .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.id", Is.is(100)))
                .andExpect(jsonPath("$.email", Is.is("0@gmail.com")))
                .andExpect(jsonPath("$.fullName", Is.is("name100")))
                .andExpect(jsonPath("$.city", Is.is("Moscow")))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink100.com")))
                .andExpect(jsonPath("$.reputation", Is.is(0)))
                .andExpect(status().isOk());

        //Test 3. Negative test. Addressing a non-existent question is an error
        this.mvc.perform(get("/api/user/question/{id}/comments", 105)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$", Is.is("No question with such id")));

        //Test 4. Positive. We take a question from the database by id
        this.mvc.perform(get("/api/user/question/{id}", 104)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Is.is(104)))
                .andExpect(jsonPath("$.authorId", Is.is(104)))
                .andExpect(jsonPath("$.authorReputation", Is.is(15)))
                .andExpect(jsonPath("$.authorName", Is.is("name104")))
                .andExpect(jsonPath("$.authorImage", Is.is("http://imagelink104.com")))
                .andExpect(jsonPath("$.description", Is.is("description5")))
                .andExpect(jsonPath("$.title", Is.is("title5")))
                .andExpect(jsonPath("$.persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(status().isOk());


        //Test 5. Positive. We receive comments on 103 questions (no comments)
        this.mvc.perform(get("/api/user/question/{id}/comments", 103)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)))
                .andExpect(status().isOk());

        //Test 6. Positive. We receive comments on 104 questions (three comments expected)
        this.mvc.perform(get("/api/user/question/{id}/comments", 104)
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].questionId", Is.is(104)))
                .andExpect(jsonPath("$.[0].lastRedactionDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[0].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[0].text", Is.is("Comment on the question nickname nickname104")))
                .andExpect(jsonPath("$.[0].userId", Is.is(101)))
                .andExpect(jsonPath("$.[0].imageLink", Is.is("http://imagelink101.com")))
                .andExpect(jsonPath("$.[0].reputation", Is.is(15)))
                .andExpect(jsonPath("$.[1].id", Is.is(101)))
                .andExpect(jsonPath("$.[1].questionId", Is.is(104)))
                .andExpect(jsonPath("$.[1].lastRedactionDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[1].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[1].text", Is.is("Comment on the question nickname nickname104")))
                .andExpect(jsonPath("$.[1].userId", Is.is(102)))
                .andExpect(jsonPath("$.[1].imageLink", Is.is("http://imagelink102.com")))
                .andExpect(jsonPath("$.[1].reputation", Is.is(15)))
                .andExpect(jsonPath("$.[2].id", Is.is(102)))
                .andExpect(jsonPath("$.[2].questionId", Is.is(104)))
                .andExpect(jsonPath("$.[2].lastRedactionDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[2].persistDate", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.[2].text", Is.is("Comment on the question nickname nickname104")))
                .andExpect(jsonPath("$.[2].userId", Is.is(103)))
                .andExpect(jsonPath("$.[2].imageLink", Is.is("http://imagelink103.com")))
                .andExpect(jsonPath("$.[2].reputation", Is.is(15)))
                .andExpect(status().isOk());
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListMostPopularQuestionDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListMostPopularQuestionDto/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getPageWithListMostPopularQuestionDtoTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //Test 1. Positive.  Most popular questions without tags in the query (page number and number of items per page by default)
        this.mvc.perform(get("/api/user/question/mostPopular")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].title", Is.is("title1")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(45)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name1")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(2)))
                //Total weight = 18; view = 2; answer = 3; votes = 2. (2+ 3*2 + 2*5 = 18)
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245126")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].listTagDto[1].id", Is.is(103)))

                .andExpect(jsonPath("$.items[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[1].viewCount", Is.is(1)))
                .andExpect(jsonPath("$.items[1].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(1)))
                //Total weight = 8; view = 1; answer = 1; votes = 1. (1 + 1*2 + 1*5 = 8)

                .andExpect(jsonPath("$.items[2].id", Is.is(101)))
                .andExpect(jsonPath("$.items[2].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.items[2].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[2].countValuable", Is.is(0)))
                //Total weight = 4; view = 2; answer = 1; votes = 0. (2 + 1*2 + 0*5 = 4)

                .andExpect(jsonPath("$.items[3].id", Is.is(104)))
                .andExpect(jsonPath("$.items[3].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[3].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countValuable", Is.is(0)))
                //Total weight = 3; view = 3; answer = 0; votes = 0. (3 + 0*2 + 0*5 = 3)

                .andExpect(jsonPath("$.items[4].id", Is.is(103)))
                .andExpect(jsonPath("$.items[4].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.items[4].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countValuable", Is.is(0)))
                //Total weight = 2; view = 2; answer = 0; votes = 0. (2 + 0*2 + 0*5 = 2)

                .andExpect(jsonPath("$.items[5].id", Is.is(105)))
                .andExpect(jsonPath("$.items[5].viewCount", Is.is(1)))
                .andExpect(jsonPath("$.items[5].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[5].countValuable", Is.is(0)))
                //Total weight = 1; view = 1; answer = 0; votes = 0. (1 + 0*2 + 0*5 = 1)

                .andExpect(jsonPath("$.items.size()", Is.is(10)))
                .andExpect(status().isOk());

        //Test 2. Positive. Top questions with ignored tags in a query
        //(page number and number of elements per page are passed in parameters)
        this.mvc.perform(get("/api/user/question/mostPopular")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "2")
                        .param("items", "5")
                        .param("ignoredTags", "100", "103", "106"))

                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(10)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(5)))

                .andExpect(jsonPath("$.items[0].id", Is.is(112)))
                .andExpect(jsonPath("$.items[0].title", Is.is("title13")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(102)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(45)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description13")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-01-27T13:01:11.245138")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245138")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(105)))

                .andExpect(jsonPath("$.items[1].id", Is.is(113)))
                .andExpect(jsonPath("$.items[1].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[1].listTagDto[0].id", Is.is(105)))

                .andExpect(jsonPath("$.items[2].id", Is.is(114)))
                .andExpect(jsonPath("$.items[2].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[2].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[2].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[2].listTagDto[0].id", Is.is(105)))

                .andExpect(jsonPath("$.items[3].id", Is.is(108)))
                .andExpect(jsonPath("$.items[3].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[3].listTagDto[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[3].listTagDto[1].id", Is.is(104)))

                .andExpect(jsonPath("$.items[4].id", Is.is(109)))
                .andExpect(jsonPath("$.items[4].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.items[4].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[4].listTagDto[1].id", Is.is(105)))
                .andExpect(jsonPath("$.items.size()", Is.is(5)))
                .andExpect(status().isOk());

        //Test 3. Positive.  Successful test on not existing page
        this.mvc.perform(get("/api/user/question/mostPopular")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "5"))
                .andDo(print())
                .andExpect(jsonPath("$.items").isEmpty())
                .andExpect(status().isOk());

        //Test 4. Negative test.  Page with negative number can not be found
        this.mvc.perform(get("/api/user/question/mostPopular")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListQuestionDtoSortedByNewest/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListQuestionDtoSortedByNewest/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getPageWithListQuestionDtoSortedByNewestTest() throws Exception {
        String token = getToken("5@gmail.com", "5pwd");

        //Test 1. Positive. The request parameters are passed page, items.
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "2")
                        .param("items", "5")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(2)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(10)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(5)))
                .andExpect(jsonPath("$.items[0].id", Is.is(105)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-03-11T17:18:55.740637")))
                .andExpect(jsonPath("$.items[1].id", Is.is(104)))
                .andExpect(jsonPath("$.items[1].persistDateTime", Is.is("2023-03-11T17:18:55.723817")))
                .andExpect(jsonPath("$.items[2].id", Is.is(103)))
                .andExpect(jsonPath("$.items[2].persistDateTime", Is.is("2023-03-11T17:18:55.704083")))
                .andExpect(jsonPath("$.items[3].id", Is.is(102)))
                .andExpect(jsonPath("$.items[3].persistDateTime", Is.is("2023-03-11T17:18:55.66548")))
                .andExpect(jsonPath("$.items[4].id", Is.is(101)))
                .andExpect(jsonPath("$.items[4].persistDateTime", Is.is("2023-03-11T17:18:55.618418")));

        //Test 2. Positive. The request parameters are passed page, items, trackedTag, ignoredTag
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "3")
                        .param("items", "2")
                        .param("trackedTag", "101,102")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(3)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(104)))
                .andExpect(jsonPath("$.items[0].title", Is.is("question user id 104")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(104)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(15)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("superfullname3")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("https://img.com/3")))
                .andExpect(jsonPath("$.items[0].description", Is.is("Asked by user id 104")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(-1)))
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-03-11T17:18:55.723817")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-03-11T17:18:55.723817")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].name", Is.is("Java")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].description", Is.is("Java")));

        //Test 3. Positive. The request parameters are passed page, items, trackedTag
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "3")
                        .param("items", "2")
                        .param("trackedTag", "101,102")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(3)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(9)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(2)))
                .andExpect(jsonPath("$.items[0].id", Is.is(105)))
                .andExpect(jsonPath("$.items[0].title", Is.is("question user id 105")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(105)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(15)))
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

        //Test 4. Positive. The request parameters are passed page, ignoredTag
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(6)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(110)))
                .andExpect(jsonPath("$.items[0].listTagDto.size()", Is.is(1)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].id", Is.is(107)))
                .andExpect(jsonPath("$.items[1].listTagDto.size()", Is.is(2)))
                .andExpect(jsonPath("$.items[1].listTagDto[0].id", Is.is(101)))
                .andExpect(jsonPath("$.items[1].listTagDto[1].id", Is.is(102)))
                .andExpect(jsonPath("$.items[2].id", Is.is(106)))
                .andExpect(jsonPath("$.items[2].listTagDto.size()", Is.is(1)))
                .andExpect(jsonPath("$.items[2].listTagDto[0].id", Is.is(101)));

        //Test 5. Negative test. Incorrect page and correct ignoredTag were passed
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "0")
                        .param("ignoredTag", "103,104")
                )
                .andExpect(status().is4xxClientError());

        //Test 6. Negative test. The correct page and incorrect items were passed
        this.mvc.perform(get("/api/user/question/new")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("page", "1")
                        .param("items", "0")
                )
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testAddCommentForQuestionById/Before.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testAddCommentForQuestionById/After.sql"},
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void addCommentForQuestionByIdTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        // Check adding Comment to existing Question
        this.mvc.perform(post("/api/user/question/{questionId}/comment", 100)
                        .header("Authorization", "Bearer " + token)
                        .content("Test Comment"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",Is.is(1)))
                .andExpect(jsonPath("$.questionId", Is.is(100)))
                .andExpect(jsonPath("$.text", Is.is("Test Comment")))
                .andExpect(jsonPath("$.userId", Is.is(100)))
                .andExpect(jsonPath("$.imageLink", Is.is("http://imagelink100.com")))
                .andExpect(jsonPath("$.reputation", Is.is(4)));

        // Check adding Comment to nonexistent Question
        this.mvc.perform(post("/api/user/question/{questionId}/comment", 105)
                        .header("Authorization", "Bearer " + token)
                        .content("Test Comment"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("Question with the such ID was not found")));

        // Check Adding Comment without Authorization.
        this.mvc.perform(post("/api/user/question/{questionId}/comment", 100)
                        .content("Test Comment"))
                .andDo(print())
                .andExpect(status().isForbidden());

        // Check Adding Empty Comment
        this.mvc.perform(post("/api/user/question/{questionId}/comment", 100)
                        .header("Authorization", "Bearer " + token)
                        .content(""))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }
    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testAddQuestionToCurrentUserBookmark/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testAddQuestionToCurrentUserBookmark/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testAddQuestionToCurrentUserBookmark() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");
        //Тест на добавление вопроса в закладки.
        this.mvc.perform(post("/api/user/question/{questionId}/bookmark", 100)
                .header("Authorization", "Bearer " + token).content("Test note"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Is.is(1)))
                .andExpect(jsonPath("$.questionId",Is.is(100)))
                .andExpect(jsonPath("$.userId",Is.is(100)))
                .andExpect(jsonPath("$.note", Is.is("Test note")));
        //Тест на добавление несуществующего вопроса.
        this.mvc.perform(post("/api/user/question/{questionId}/bookmark", 120)
                        .header("Authorization", "Bearer " + token).content("Test note"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$", Is.is("Question not found")));
        //Тест на добавление одного и того же вопроса.
        this.mvc.perform(post("/api/user/question/{questionId}/bookmark", 100)
                        .header("Authorization", "Bearer " + token).content("Test note"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$", Is.is("Question already exist")));
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListMostPopularQuestionForMonthDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = {"/script/TestQuestionResourceController/testGetPageWithListMostPopularQuestionForMonthDto/After.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void getPageWithListMostPopularQuestionForMonthDtoTest() throws Exception {
        String token = getToken("0@gmail.com", "0pwd");

        //Test 1. Positive.  Most popular questions without tags in the query (page number and number of items per page by default)
        this.mvc.perform(get("/api/user/question/mostPopularForMonth")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(2)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(15)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))

                .andExpect(jsonPath("$.items[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].title", Is.is("title3")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(102)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(45)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description3")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(7)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(1)))
                //Total weight = 22; view = 3; answer = 7; votes = 1. (3+ 7*2 + 1*5 = 22)
                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-01-27T13:01:11.245128")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245128")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].listTagDto[1].id", Is.is(105)))

                .andExpect(jsonPath("$.items[1].id", Is.is(103)))
                .andExpect(jsonPath("$.items[1].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[1].countAnswer", Is.is(6)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(1)))
                //Total weight = 22; view = 3; answer = 6; votes = 1. (3 + 6*2 + 1*5 = 20)

                .andExpect(jsonPath("$.items[2].id", Is.is(100)))
                .andExpect(jsonPath("$.items[2].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[2].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[2].countValuable", Is.is(1)))
                //Total weight = 4; view = 0; answer = 1; votes = 1. (0 + 1*2 + 1*5 = 7)

                .andExpect(jsonPath("$.items[3].id", Is.is(101)))
                .andExpect(jsonPath("$.items[3].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countValuable", Is.is(1)))
                //Total weight = 5; view = 0; answer = 0; votes = 0. (0 + 0*2 + 1*5 = 5)

                .andExpect(jsonPath("$.items[4].id", Is.is(105)))
                .andExpect(jsonPath("$.items[4].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countAnswer", Is.is(2)))
                .andExpect(jsonPath("$.items[4].countValuable", Is.is(0)))
                //Total weight = 4; view = 0; answer = 0; votes = 0. (0 + 2*2 + 0*5 = 4)

                .andExpect(jsonPath("$.items[5].id", Is.is(106)))
                .andExpect(jsonPath("$.items[5].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[5].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[5].countValuable", Is.is(0)))
                //Total weight = 3; view = 0; answer = 0; votes = 0. (3 + 0*2 + 0*5 = 3)

                .andExpect(jsonPath("$.items[6].id", Is.is(107)))
                .andExpect(jsonPath("$.items[6].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[6].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[6].countValuable", Is.is(0)))
                //Total weight = 2; view = 0; answer = 0; votes = 0. (0 + 1*2 + 0*5 = 2)

                .andExpect(jsonPath("$.items.size()", Is.is(10)))
                .andExpect(status().isOk());

        //Test 2. Positive. Top questions with ignored tags in a query
        //(page number and number of elements per page are passed in parameters)
        this.mvc.perform(get("/api/user/question/mostPopularForMonth")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "1")
                        .param("items", "5")
                        .param("ignoredTags", "100, 108"))

                .andDo(print())
                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(3)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(12)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(5)))

                .andExpect(jsonPath("$.items[0].authorId", Is.is(102)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(45)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description3")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(7)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(1)))

                .andExpect(jsonPath("$.items[0].persistDateTime", Is.is("2023-01-27T13:01:11.245128")))
                .andExpect(jsonPath("$.items[0].lastUpdateDateTime", Is.is("2023-01-27T13:01:11.245128")))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.items[0].listTagDto[1].id", Is.is(105)))


                .andExpect(jsonPath("$.items[1].id", Is.is(103)))
                .andExpect(jsonPath("$.items[1].viewCount", Is.is(3)))
                .andExpect(jsonPath("$.items[1].countAnswer", Is.is(6)))
                .andExpect(jsonPath("$.items[1].countValuable", Is.is(1)))
                //Total weight = 22; view = 3; answer = 6; votes = 1. (3 + 6*2 + 1*5 = 20)

                .andExpect(jsonPath("$.items[2].id", Is.is(101)))
                .andExpect(jsonPath("$.items[2].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[2].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.items[2].countValuable", Is.is(1)))

                .andExpect(jsonPath("$.items[3].id", Is.is(105)))
                .andExpect(jsonPath("$.items[3].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[3].countAnswer", Is.is(2)))
                .andExpect(jsonPath("$.items[3].countValuable", Is.is(0)))

                .andExpect(jsonPath("$.items[4].id", Is.is(107)))
                .andExpect(jsonPath("$.items[4].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[4].countAnswer", Is.is(1)))
                .andExpect(jsonPath("$.items[4].countValuable", Is.is(0)))

                .andExpect(jsonPath("$.items.size()", Is.is(5)))
                .andExpect(status().isOk());

        //Test 3. Positive.  Successful test on not existing page
        this.mvc.perform(get("/api/user/question/mostPopularForMonth")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "5"))
                .andDo(print())
                .andExpect(jsonPath("$.items").isEmpty())
                .andExpect(status().isOk());

        //Test 4. Negative test.  Page with negative number can not be found
        this.mvc.perform(get("/api/user/question/mostPopularForMonth")
                        .header("Authorization", "Bearer " + token)
                        .param("page", "-1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testAddViewForQuestion/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testAddViewForQuestion/After.sql"})
    })
    public void testAddViewForQuestion() throws Exception {
        String token1 = getToken("0@gmail.com", "0pwd");

        // Тест - юзер заходит на разные вопросы, происходит проверка на то, осуществлена ли запись данных о просмотре вопроса
        this.mvc.perform(post("/api/user/question/{questionId}/view", 100).header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from QuestionViewed a where a.question.id = 100 and a.user.id = 100")
                .getResultList()
                .size())
                .isEqualTo(1);

        this.mvc.perform(post("/api/user/question/{questionId}/view", 101).header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from QuestionViewed a where a.question.id = 101 and a.user.id = 100")
                .getResultList()
                .size())
                .isEqualTo(1);


        // Тест на проверку, не осуществляется ли повторная запись просмотра вопроса пользователем
        this.mvc.perform(post("/api/user/question/{questionId}/view", 102).header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from QuestionViewed a where a.question.id = 102 and a.user.id = 100")
                .getResultList()
                .size())
                .isEqualTo(1);
        this.mvc.perform(post("/api/user/question/{questionId}/view", 102).header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from QuestionViewed a where a.question.id = 102 and a.user.id = 100")
                .getResultList()
                .size())
                .isEqualTo(1);
        // Тест на проверку записи просмотра на несуществующий вопрос
        this.mvc.perform(post("/api/user/question/{questionId}/view", 100000).header("Authorization", "Bearer " + token1))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select a from QuestionViewed a where a.question.id = 100000 and a.user.id = 100")
                .getResultList()
                .size())
                .isEqualTo(0);

    }
    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testGetQuestionsByValue/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testGetQuestionsByValue/After.sql"})
    })
    public void testGetQuestionsByValue() throws Exception {
        String token = getToken("100@gmail.com", "0pwd");

        //Поиск по названию
        this.mvc.perform(get("/api/user/question/search/?value=title1")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].title", Is.is("title1")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(100)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name1")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].id", Is.is(107)))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].questionId", Is.is(100)));

        //Поиск по тегу
        this.mvc.perform(get("/api/user/question/search/?value=[tag with id 106]")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(2)))
                .andExpect(jsonPath("$.[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[0].title", Is.is("title3")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(102)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description3")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.[0].listTagDto[1].id", Is.is(106)))
                .andExpect(jsonPath("$.[0].listTagDto[1].name", Is.is("tag with id 106")))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].questionId", Is.is(102)))
                .andExpect(jsonPath("$.[1].id", Is.is(109)))
                .andExpect(jsonPath("$.[1].title", Is.is("title10")))
                .andExpect(jsonPath("$.[1].authorId", Is.is(104)))
                .andExpect(jsonPath("$.[1].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[1].authorName", Is.is("name5")))
                .andExpect(jsonPath("$.[1].authorImage", Is.is("http://imagelink5.com")))
                .andExpect(jsonPath("$.[1].description", Is.is("description10")))
                .andExpect(jsonPath("$.[1].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.[1].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.[1].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[1].listTagDto[0].id", Is.is(103)))
                .andExpect(jsonPath("$.[1].listTagDto[0].name", Is.is("tag with id 103")))
                .andExpect(jsonPath("$.[1].listTagDto[1].id", Is.is(106)))
                .andExpect(jsonPath("$.[1].listTagDto[1].name", Is.is("tag with id 106")));
        // Поиск по user
        this.mvc.perform(get("/api/user/question/search/?value=user:105")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(1)))
                .andExpect(jsonPath("$.[0].id", Is.is(114)))
                .andExpect(jsonPath("$.[0].title", Is.is("title15")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(105)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name6")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink6.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description15")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 100")));
        // Поиск по answers
        this.mvc.perform(get("/api/user/question/search/?value=answers:3")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(2)))
                .andExpect(jsonPath("$.[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].title", Is.is("title1")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(100)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name1")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].id", Is.is(107)))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].questionId", Is.is(100)))
                .andExpect(jsonPath("$.[1].id", Is.is(102)))
                .andExpect(jsonPath("$.[1].title", Is.is("title3")))
                .andExpect(jsonPath("$.[1].authorId", Is.is(102)))
                .andExpect(jsonPath("$.[1].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[1].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.[1].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.[1].description", Is.is("description3")))
                .andExpect(jsonPath("$.[1].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.[1].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.[1].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[1].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[1].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.[1].listTagDto[1].id", Is.is(106)))
                .andExpect(jsonPath("$.[1].listTagDto[1].name", Is.is("tag with id 106")))
                .andExpect(jsonPath("$.[1].listAnswerDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[1].listAnswerDto[0].questionId", Is.is(102)));
        // Поиск по views
        this.mvc.perform(get("/api/user/question/search/?value=views:3")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(1)))
                .andExpect(jsonPath("$.[0].id", Is.is(108)))
                .andExpect(jsonPath("$.[0].title", Is.is("title9")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(103)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name4")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink4.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description9")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(4)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(0)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 102")));

        // Поиск по двум параметрам tag and user
        this.mvc.perform(get("/api/user/question/search/?value=[tag with id 106] user:102")
                        .header("Authorization", "Bearer " + token))
                .andDo(print())
                .andExpect(jsonPath("$.size()", Is.is(1)))
                .andExpect(jsonPath("$.[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[0].title", Is.is("title3")))
                .andExpect(jsonPath("$.[0].authorId", Is.is(102)))
                .andExpect(jsonPath("$.[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.[0].authorName", Is.is("name3")))
                .andExpect(jsonPath("$.[0].authorImage", Is.is("http://imagelink3.com")))
                .andExpect(jsonPath("$.[0].description", Is.is("description3")))
                .andExpect(jsonPath("$.[0].viewCount", Is.is(2)))
                .andExpect(jsonPath("$.[0].countAnswer", Is.is(3)))
                .andExpect(jsonPath("$.[0].countValuable", Is.is(0)))
                .andExpect(jsonPath("$.[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.[0].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.[0].listTagDto[1].id", Is.is(106)))
                .andExpect(jsonPath("$.[0].listTagDto[1].name", Is.is("tag with id 106")))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].id", Is.is(102)))
                .andExpect(jsonPath("$.[0].listAnswerDto[0].questionId", Is.is(102)));
    }

    @Test
    @SqlGroup({
            @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testGetQuestionDtoListByTag/Before.sql"}),
            @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD,
                    value = {"/script/TestQuestionResourceController/testGetQuestionDtoListByTag/After.sql"})
    })
    public void testGetQuestionDtoListByTag() throws Exception {
        String JWT = getToken("100@gmail.com", "0pwd");


//      positive get test
        this.mvc.perform(get("/api/user/question/tag")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tagName", "tag with id 100")
                        .param("pageNumber", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                .andExpect(jsonPath("$.currentPageNumber", Is.is(1)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(1)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(10)))
                .andExpect(jsonPath("$.items[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].title", Is.is("title1")))
                .andExpect(jsonPath("$.items[0].authorId", Is.is(100)))
                .andExpect(jsonPath("$.items[0].authorReputation", Is.is(0)))
                .andExpect(jsonPath("$.items[0].authorName", Is.is("name1")))
                .andExpect(jsonPath("$.items[0].authorImage", Is.is("http://imagelink1.com")))
                .andExpect(jsonPath("$.items[0].description", Is.is("description1")))
                .andExpect(jsonPath("$.items[0].viewCount", Is.is(0)))
                .andExpect(jsonPath("$.items[0].countAnswer", Is.is(4)))
                .andExpect(jsonPath("$.items[0].countValuable", Is.is(2)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].id", Is.is(100)))
                .andExpect(jsonPath("$.items[0].listTagDto[0].name", Is.is("tag with id 100")))
                .andExpect(jsonPath("$.items[0].listAnswerDto[0].id", Is.is(104)))
                .andExpect(jsonPath("$.items[0].listAnswerDto[0].questionId", Is.is(100)));

//        positive pagination test

        this.mvc.perform(get("/api/user/question/tag")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tagName", "tag with id 100")
                        .param("pageNumber", "5")
                        .param("items", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.currentPageNumber", Is.is(5)))
                .andExpect(jsonPath("$.totalPageCount", Is.is(5)))
                .andExpect(jsonPath("$.totalResultCount", Is.is(5)))
                .andExpect(jsonPath("$.itemsOnPage", Is.is(1)))
                .andExpect(jsonPath("$.items[0].id", Is.is(114)));


//        negative pagination test. No page number

        this.mvc.perform(get("/api/user/question/tag")
                        .header("Authorization", "Bearer " + JWT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("tagName", "tag with id 100")
                        .param("items", "1"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    @Sql(value = {"/script/TestQuestionResourceController/testGetCountQuestionDto/Before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
    @Sql(value = "/script/TestQuestionResourceController/testGetCountQuestionDto/After.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    public void testGetCountQuestionDto() throws Exception {
        String JWT = getToken("0@gmail.com", "0pwd");

        this.mvc.perform(get("/api/user/question/count")
                        .header("Authorization", "Bearer " + JWT))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select count(*) from Question").getSingleResult())
                .isEqualTo(5L);

//        Test on zero question
        this.mvc.perform(get("/api/user/question/count")
                        .header("Authorization", "Bearer " + JWT))
                .andDo(print())
                .andExpect(status().isOk());
        assertThat(em.createQuery("select count(*) from Question q where q.id > 105").getSingleResult())
                .isEqualTo(0L);
    }

}