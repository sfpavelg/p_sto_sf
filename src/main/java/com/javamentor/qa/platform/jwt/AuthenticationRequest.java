package com.javamentor.qa.platform.jwt;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthenticationRequest {

    private String email;
    private String password;
}
