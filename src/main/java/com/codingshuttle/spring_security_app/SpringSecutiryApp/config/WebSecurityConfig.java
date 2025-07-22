package com.codingshuttle.spring_security_app.SpringSecutiryApp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    SecurityFilterChain createSecurityFilterChain(HttpSecurity httpSecurity) throws Exception {

    }

}
