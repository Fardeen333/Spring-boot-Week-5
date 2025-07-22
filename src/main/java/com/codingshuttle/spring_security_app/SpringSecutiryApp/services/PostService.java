package com.codingshuttle.spring_security_app.SpringSecutiryApp.services;



import com.codingshuttle.spring_security_app.SpringSecutiryApp.dto.PostDTO;

import java.util.List;

public interface PostService {

    List<PostDTO> getAllPosts();

    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);
}
