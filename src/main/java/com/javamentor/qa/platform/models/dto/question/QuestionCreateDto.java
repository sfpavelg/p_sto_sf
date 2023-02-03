package com.javamentor.qa.platform.models.dto.question;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateDto {

    @NotBlank
    private String title;
    @NotBlank
    private String description;

    private List<TagDto> tags = new ArrayList<>();


}
