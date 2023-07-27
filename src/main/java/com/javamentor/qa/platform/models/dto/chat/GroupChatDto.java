package com.javamentor.qa.platform.models.dto.chat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class GroupChatDto {

    private Long id;
    private String chatName;
    private LocalDateTime persistDate;
    private String lastMessage;
    private String imageLink;

}
