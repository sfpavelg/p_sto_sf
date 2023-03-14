package com.javamentor.qa.platform.security.jwt;


import com.javamentor.qa.platform.models.entity.user.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
@Api("Контроллер получения JWT токена")
public class AuthenticationResourceController {


    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResourceController(JwtService jwtService, AuthenticationManager authenticationManager) {
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/token")
    @ApiOperation("Получение JWT токена и авторизации по email и password")
    public AuthenticationResponse authentication(@RequestBody AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        return new AuthenticationResponse(jwtService.generateJwtToken(authentication));
    }

    @GetMapping("/validate")
    public String validate(@AuthenticationPrincipal User user){
        return user.getRole().getName();
    }
}
