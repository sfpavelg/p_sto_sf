package com.javamentor.qa.platform.models.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
    private boolean remember;

    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
