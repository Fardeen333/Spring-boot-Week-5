package com.codingshuttle.spring_security_app.SpringSecutiryApp.dto;

import lombok.Data;

import java.util.Set;

@Data
public class SignUpDto {
    private String email;
    private String password;
    private String name;
}
