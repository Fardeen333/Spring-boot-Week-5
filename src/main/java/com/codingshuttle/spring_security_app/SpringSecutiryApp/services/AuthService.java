package com.codingshuttle.spring_security_app.SpringSecutiryApp.services;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginResponseDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;


    public LoginResponseDto login(LoginDto payload) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = jwtService.generateRefreshToken(user);

        return new LoginResponseDto(user.getId(), accessToken, refreshToken);

    }

    public LoginResponseDto refresh(String refreshToken) {

        Long userId = jwtService.getUserIdFromToken(refreshToken);

        User user = userService.getUserById(userId);
        String accessToken = jwtService.generateAccessToken(user);
        return new LoginResponseDto(user.getId(), accessToken, refreshToken);
    }
}
