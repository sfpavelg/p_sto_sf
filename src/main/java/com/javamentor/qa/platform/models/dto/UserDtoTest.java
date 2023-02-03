package com.javamentor.qa.platform.models.dto;

import java.time.LocalDateTime;

public class UserDtoTest {

    private String key;
    private String name;
    private Long points = 0L;

    private LocalDateTime regDate = LocalDateTime.now();

    public UserDtoTest() {
    }

    public UserDtoTest(String key, String name) {
        this.key = key;
        this.name = name;

    }

    public static UserDtoTest of(String key, String value) {
        return new UserDtoTest(key, value);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPoints() {
        return points;
    }

    public void setPoints(Long points) {
        this.points = points;
    }

    public LocalDateTime getRegDate() {
        return regDate;
    }

    public void setRegDate(LocalDateTime regDate) {
        this.regDate = regDate;
    }
}
