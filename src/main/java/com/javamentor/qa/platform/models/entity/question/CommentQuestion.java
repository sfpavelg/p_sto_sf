package com.javamentor.qa.platform.models.entity.question;

import com.javamentor.qa.platform.exception.ApiRequestException;
import com.javamentor.qa.platform.models.entity.Comment;
import com.javamentor.qa.platform.models.entity.CommentType;
import com.javamentor.qa.platform.models.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment_question")
public class CommentQuestion implements Serializable {

    private static final long serialVersionUID = -5612025796504179957L;
    @Id
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @MapsId
    private Comment comment = new Comment(CommentType.QUESTION);

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    public CommentQuestion(String text, User user) {
        comment.setText(text);
        comment.setUser(user);
    }

    @PrePersist
    private void prePersistFunction() {
        checkConstraints();
    }

    @PreUpdate
    private void preUpdateFunction() {
        checkConstraints();
    }

    private void checkConstraints() {
        if (this.comment.getCommentType() != CommentType.QUESTION) {
            throw new ApiRequestException("У экземпляра Comment, связанного с CommentQuestion, " +
                    "поле commentType должно принимать значение CommentType.QUESTION");
        }
        if (this.question == null) {
            throw new ApiRequestException("Экземпляр Question, связанный с CommentQuestion " +
                    "должен быть инициализирован и содержать корректные значения");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentQuestion that = (CommentQuestion) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setText(String text){
        comment.setText(text);
    }

    public void setUser(User user){
        comment.setUser(user);
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }
}
