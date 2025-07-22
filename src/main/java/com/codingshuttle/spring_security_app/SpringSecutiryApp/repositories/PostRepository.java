package com.codingshuttle.spring_security_app.SpringSecutiryApp.repositories;

import com.codingshuttle.spring_security_app.SpringSecutiryApp.entities.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
