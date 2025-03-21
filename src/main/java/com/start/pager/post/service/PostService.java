package com.start.pager.post.service;

import com.start.pager.post.dto.PostDto;
import com.start.pager.post.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    ResponseEntity<String> createPostWithData(PostDto post, UserDetails userDetails);

    List<Post> findAllPosts();
}
