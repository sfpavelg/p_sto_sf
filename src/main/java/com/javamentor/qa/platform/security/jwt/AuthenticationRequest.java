package com.javamentor.qa.platform.security.jwt;


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
