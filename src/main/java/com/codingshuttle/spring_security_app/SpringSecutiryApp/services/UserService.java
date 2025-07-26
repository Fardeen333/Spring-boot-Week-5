package com.codingshuttle.spring_security_app.SpringSecutiryApp.services;


import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.LoginDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.SignUpDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.UserDto;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.entities.User;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new BadCredentialsException("User with email "+ username +" not found"));
    }

    public User getUserById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new BadCredentialsException("User with email "+ id +" not found"));
    }


    public UserDto signUp(SignUpDto payload) {

        Optional<User> existingUser = userRepository.findByEmail(payload.getEmail());
        if(existingUser.isPresent()){
            throw new BadCredentialsException("User with email already exists : " + payload.getEmail());
        }

        User toBeCreated= modelMapper.map(payload, User.class);
        toBeCreated.setPassword(passwordEncoder.encode(toBeCreated.getPassword()));


        User savedUser = userRepository.save(toBeCreated);
        return modelMapper.map(savedUser, UserDto.class);
    }
}






















