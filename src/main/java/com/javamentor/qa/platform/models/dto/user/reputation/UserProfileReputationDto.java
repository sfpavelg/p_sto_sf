package com.javamentor.qa.platform.models.dto.user.reputation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfileReputationDto {
    private int countReputation;
    private LocalDateTime persistDate;
    private ReputationHistoryType action;
}
