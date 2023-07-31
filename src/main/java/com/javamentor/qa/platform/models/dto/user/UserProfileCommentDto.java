package com.javamentor.qa.platform.models.dto.user;

import com.javamentor.qa.platform.models.entity.CommentType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileCommentDto {

    private Long id;
    private String comment;
    private LocalDateTime persistDate;
    private Long questionId;
    private Long answerId;
    private CommentType commentType;

    public UserProfileCommentDto(Long id, String comment, LocalDateTime persistDate, int questionId, Long answerId, CommentType commentType) {
        this.id = id;
        this.comment = comment;
        this.persistDate = persistDate;
        this.questionId = Long.valueOf(questionId);
        this.answerId = answerId;
        this.commentType = commentType;
    }

}
