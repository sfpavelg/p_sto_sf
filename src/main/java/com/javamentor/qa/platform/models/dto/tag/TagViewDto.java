package com.javamentor.qa.platform.models.dto.tag;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagViewDto {

    private Long id;
    private String title;
    private String description;
    private Long questionCount;
    private Long questionCountOneDay;
    private Long questionCountWeekDay;

    public TagViewDto(Long id, String title, String description, int questionCount, int questionCountOneDay, int questionCountWeekDay) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.questionCount = Long.valueOf(questionCount);
        this.questionCountOneDay = Long.valueOf(questionCountOneDay);
        this.questionCountWeekDay = Long.valueOf(questionCountWeekDay);
    }
}

