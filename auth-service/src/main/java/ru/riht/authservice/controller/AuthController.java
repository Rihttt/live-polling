package ru.riht.authservice.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.riht.authservice.domain.dto.JwtAuthResponse;
import ru.riht.authservice.domain.dto.LoginRequest;
import ru.riht.authservice.domain.dto.RegisterRequest;
import ru.riht.authservice.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public JwtAuthResponse signUp(@RequestBody @Valid RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/sign-in")
    public JwtAuthResponse signIn(@RequestBody @Valid LoginRequest request) {
        return authService.login(request);
    }
}
