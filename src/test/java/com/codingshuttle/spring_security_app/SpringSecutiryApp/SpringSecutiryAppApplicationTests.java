package com.codingshuttle.spring_security_app.SpringSecutiryApp;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.entities.User;
import com.codingshuttle.spring_security_app.SpringSecutiryApp.services.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringSecutiryAppApplicationTests {

	@Autowired
	private JwtService jwtService;

//	@Test
//	void contextLoads() {
//		User user = new User(4l, "xfardeen333@gmail.com", "Swift@5140");
//
//		String jwtToken = jwtService.generateAccessToken(user);
//
//		System.out.println(jwtToken);
//
//		Long id = jwtService.getUserIdFromToken(jwtToken);
//
//		System.out.println(id);
//	}

}
