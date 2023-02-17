package com.javamentor.qa.platform.models.dto.answer;

import java.time.LocalDateTime;

public class AnswerDto {
    private Long id;
    private Long userId;
    private Long userReputation;
    private Long questionId;
    private String body;
    private LocalDateTime persistDate;
    private Boolean isHelpful;
    private LocalDateTime dateAccept;
    private Long countValuable;
    private String image;
    private String nickName;


    public AnswerDto(Long id, Long userId, Long userReputation, Long questionId, String body, LocalDateTime persistDate, Boolean isHelpful, LocalDateTime dateAccept, Long countValuable, String image, String nickName) {
        this.id = id;
        this.userId = userId;
        this.userReputation = userReputation;
        this.questionId = questionId;
        this.body = body;
        this.persistDate = persistDate;
        this.isHelpful = isHelpful;
        this.dateAccept = dateAccept;
        this.countValuable = countValuable;
        this.image = image;
        this.nickName = nickName;
    }
}
