package com.codingshuttle.spring_security_app.SpringSecutiryApp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;

    private String accessToken;

    private String refreshToken;

}
