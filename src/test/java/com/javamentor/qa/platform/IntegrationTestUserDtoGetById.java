package com.javamentor.qa.platform;

import com.javamentor.qa.platform.webapp.configs.JmApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = JmApplication.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class IntegrationTestUserDtoGetById {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @Sql({"/test-user-data.sql"})
    public void testGetUserById() {

        ResponseEntity<String> responseEntity = testRestTemplate
                .getForEntity("/api/user/1", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("{\"id\":1,\"email\":\"email1@domain.com\",\"fullName\":\"name1\",\"city\":\"moscow\",\"imageLink\":\"http://imagelink1.com\",\"reputation\":6}", responseEntity.getBody());

        responseEntity = testRestTemplate
                .getForEntity("/api/user/2", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("{\"id\":2,\"email\":\"email2@domain.com\",\"fullName\":\"name2\",\"city\":\"spb\",\"imageLink\":\"http://imagelink2.com\",\"reputation\":14}", responseEntity.getBody());

        responseEntity = testRestTemplate
                .getForEntity("/api/user/3", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("{\"id\":3,\"email\":\"email3@domain.com\",\"fullName\":\"name3\",\"city\":\"NY\",\"imageLink\":\"http://imagelink3.com\",\"reputation\":11}", responseEntity.getBody());

        responseEntity = testRestTemplate
                .getForEntity("/api/user/4", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("{\"id\":4,\"email\":\"email4@domain.com\",\"fullName\":\"name4\",\"city\":\"spb\",\"imageLink\":\"http://imagelink4.com\",\"reputation\":8}", responseEntity.getBody());

        responseEntity = testRestTemplate
                .getForEntity("/api/user/1337", String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("null", responseEntity.getBody());

    }

}
