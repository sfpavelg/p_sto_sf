package com.javamentor.qa.platform.jwt;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationResourceController {


    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResourceController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    public AuthenticationResponse authentication(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new AuthenticationResponse(jwtService.generateJwtToken(authentication));
    }
}
