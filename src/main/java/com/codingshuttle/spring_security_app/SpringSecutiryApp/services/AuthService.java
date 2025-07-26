package com.codingshuttle.spring_security_app.SpringSecutiryApp.services;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginDto;
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


    public String login(LoginDto payload) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(payload.getEmail(), payload.getPassword())
        );

        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateAccessToken(user);
        return token;

    }
}
