package ru.riht.authservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.riht.authservice.constants.UserRoles;
import ru.riht.authservice.domain.dto.JwtAuthResponse;
import ru.riht.authservice.domain.dto.LoginRequest;
import ru.riht.authservice.domain.dto.RegisterRequest;
import ru.riht.authservice.domain.model.User;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserService userService;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;


    public JwtAuthResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .username(registerRequest.getUsername())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(UserRoles.ROLE_USER)
                .build();

        userService.create(user);

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }

    public JwtAuthResponse login(LoginRequest loginRequest) {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        ));

        var user = userService
                .userDetailsService()
                .loadUserByUsername(loginRequest.getUsername());

        var jwt = jwtService.generateToken(user);
        return new JwtAuthResponse(jwt);
    }




























}
