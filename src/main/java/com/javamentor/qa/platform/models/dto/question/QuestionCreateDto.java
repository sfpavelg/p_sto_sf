package com.javamentor.qa.platform.models.dto.question;

import com.javamentor.qa.platform.models.dto.tag.TagDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionCreateDto {

    @NotBlank (message = "Title can't be empty")
    private String title;
    @NotBlank (message = "Description can't be empty")
    private String description;
    @NotEmpty (message = "One tag at least must be chosen")
    private List<TagDto> tags = new ArrayList<>();


}
