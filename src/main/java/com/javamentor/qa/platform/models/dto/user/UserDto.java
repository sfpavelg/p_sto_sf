package com.javamentor.qa.platform.models.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String email;
    private String fullName;
    private LocalDateTime persistDateTime;
    private Boolean isEnabled = true;
    private Boolean isDeleted = false;
    private String city;
    private String linkSite;
    private String linkGitHub;
    private String linkVk;
    private String about;
    private String imageLink;
    private LocalDateTime lastUpdateDateTime;
    private String nickname;
    private String roleName;
}
