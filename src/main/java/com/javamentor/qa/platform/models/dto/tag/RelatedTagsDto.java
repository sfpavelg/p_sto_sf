package com.javamentor.qa.platform.models.dto.tag;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RelatedTagsDto {
    private Long id;

    private String title;

    private Long countQuestion;

    public RelatedTagsDto(Long id, String title, int countQuestion) {
        this.id = id;
        this.title = title;
        this.countQuestion = Long.valueOf(countQuestion);
    }
}
