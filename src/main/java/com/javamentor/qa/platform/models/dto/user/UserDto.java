package com.javamentor.qa.platform.models.dto.user;

import com.javamentor.qa.platform.models.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * A DTO for the {@link User} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String fullName;
    private String city;
    private String imageLink;
    private int reputation;

}