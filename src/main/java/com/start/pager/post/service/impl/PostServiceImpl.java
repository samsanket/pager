package com.start.pager.post.service.impl;

import com.start.pager.post.dto.PostDto;
import com.start.pager.post.model.ContentType;
import com.start.pager.post.model.Post;
import com.start.pager.post.repo.PostRepo;
import com.start.pager.post.service.PostService;
import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepo postRepository;

    @Override
    public ResponseEntity<String> createPostWithData(PostDto post, UserDetails userDetails) {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User not found");
        }
        Post newPost = new Post();
        newPost.setDescription(post.getDescription());
        newPost.setImageUrl(post.getImageUrl());
        newPost.setTextContent(post.getTextContent());
        newPost.setUser(userOptional.get());
        newPost.setVideoUrl(newPost.getImageUrl());
        newPost.setContentType(ContentType.valueOfIgnoreCase(post.getContentType()));
        postRepository.save(newPost);
        return null;
    }

    @Override
    public List<Post> findAllPosts() {
    return postRepository.findAll();
    }

}
