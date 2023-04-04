package com.javamentor.qa.platform.webapp.controllers.rest;


import com.javamentor.qa.platform.models.entity.user.User;
import com.javamentor.qa.platform.models.dto.AuthenticationRequest;
import com.javamentor.qa.platform.models.dto.AuthenticationResponse;
import com.javamentor.qa.platform.security.jwt.JwtService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
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

        int timeExpire = 86400;                  // 1 day cookie expire
        if (request.isRemember()) {
            timeExpire = Integer.MAX_VALUE;     // max time cookie expire (around 1 year)
        }
        return new AuthenticationResponse(jwtService.generateJwtToken(authentication), timeExpire);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validate(@AuthenticationPrincipal User user) {
        if (user == null || !user.getRole().getName().equals("ROLE_USER")) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok().build();
    }
}
