package com.codingshuttle.spring_security_app.SpringSecutiryApp.controllers;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.SignUpDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.UserDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.services.AuthService;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.services.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signUp(@RequestBody SignUpDto payload) {
        UserDto userDto = userService.signUp(payload);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto payload,
                                        HttpServletRequest request,
                                        HttpServletResponse response) {
        String token = authService.login(payload);

        Cookie cookie = new Cookie("accessToken", token);
        cookie.setHttpOnly(true);
        response.addCookie(cookie);

        return ResponseEntity.ok(token);
    }

}
