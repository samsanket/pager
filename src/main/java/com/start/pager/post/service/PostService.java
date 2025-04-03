package com.start.pager.post.service;

import com.start.pager.post.dto.PostDto;
import com.start.pager.post.model.Comment;
import com.start.pager.post.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    ResponseEntity<String> createPostWithData(PostDto post, UserDetails userDetails);

    List<Post> findAllPosts();

    ResponseEntity<String> shareThePost(Long postId, UserDetails userDetails);

    List<Comment> getComments(Long postId);

    ResponseEntity<String> addComments(Long postId, Comment comment, UserDetails userDetails);
}
