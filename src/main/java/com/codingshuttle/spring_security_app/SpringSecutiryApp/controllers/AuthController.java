package com.codingshuttle.spring_security_app.SpringSecutiryApp.controllers;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginResponseDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.SignUpDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.UserDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.services.AuthService;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @Value("${deploy.env}")
    private String environment;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto payload) {
        UserDto userDto = userService.signUp(payload);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto payload,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        LoginResponseDto loginResponseDto = authService.login(payload);

        Cookie cookie = new Cookie("refreshToken", loginResponseDto.getRefreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure("production".equals(environment));
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDto);
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponseDto> refresh(HttpServletRequest request){

        Cookie[] cookies = request.getCookies();

        String refreshToken = Arrays.stream(cookies)
                .filter((cookie)-> cookie.getName().equals("refreshToken"))
                .findFirst()
                .map((cookie -> cookie.getValue()))
                .orElseThrow(() -> new AuthenticationServiceException("Refresh token not found inside the cookies"));

        LoginResponseDto loginResponseDto = authService.refresh(refreshToken);
        return ResponseEntity.ok(loginResponseDto);
    }

}
