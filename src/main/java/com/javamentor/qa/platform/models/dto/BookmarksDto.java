package com.javamentor.qa.platform.models.dto;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookmarksDto {

    private Long questionId;
    private String title;
    private List<TagDto> tagDtoList;
    private Long countAnswer;
    private Long countVote;
    private Long countView;
    private LocalDateTime persistQuestionDate;

    public BookmarksDto(Long questionId, String title, Long countAnswer, Long countVote, Long countView, LocalDateTime persistQuestionDate) {
        this.questionId = questionId;
        this.title = title;
        this.countAnswer = countAnswer;
        this.countVote = countVote;
        this.countView = countView;
        this.persistQuestionDate = persistQuestionDate;
    }
}
