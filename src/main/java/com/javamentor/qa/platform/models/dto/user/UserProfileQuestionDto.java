package com.javamentor.qa.platform.models.dto.user;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UserProfileQuestionDto implements Serializable {

    private Long questionId;
    private String title;
    private List<TagDto> tags;
    private int countAnswer;
    private LocalDateTime persistDate;

    public UserProfileQuestionDto(Long questionId, String title, int countAnswer, LocalDateTime persistDate) {
        this.questionId = questionId;
        this.title = title;
        this.countAnswer = countAnswer;
        this.persistDate = persistDate;
    }
}