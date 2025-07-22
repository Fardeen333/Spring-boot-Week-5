package com.codingshuttle.spring_security_app.SpringSecutiryApp.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
