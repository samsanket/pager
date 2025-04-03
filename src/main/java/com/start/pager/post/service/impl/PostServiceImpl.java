package com.start.pager.post.service.impl;

import com.start.pager.post.dto.PostDto;
import com.start.pager.post.model.*;
import com.start.pager.post.repo.CommentRepo;
import com.start.pager.post.repo.PostRepo;
import com.start.pager.post.repo.ShareRepo;
import com.start.pager.post.repo.UserFeedRepo;
import com.start.pager.post.service.PostService;
import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PostRepo postRepository;

    @Autowired
    ShareRepo shareRepository;

    @Autowired
    UserFeedRepo userFeedRepository;


    @Autowired
    CommentRepo commentRepository;


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

    @Override
    public ResponseEntity<String> shareThePost(Long postId, UserDetails userDetails) {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        Optional<Post> postOptional = postRepository.findById(postId);
        if (userOptional.isEmpty() || postOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User or Post not found");
        }
        Share share = new Share();
        share.setUser(userOptional.get());
        share.setPost(postOptional.get());
        share.setSharedAt(new Timestamp(System.currentTimeMillis()));
        shareRepository.save(share);
        UserFeed feed = new UserFeed();
        feed.setUser(userOptional.get());
        feed.setPost(postOptional.get());
        feed.setShared(true);
        feed.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userFeedRepository.save(feed);
        return null;
    }


    @Override
    public List<Comment> getComments(Long postId) {
        List<Comment> comments = commentRepository.findByPost_PostId(postId);
        return comments;
    }


    @Override
    public ResponseEntity<String> addComments(Long postId, Comment comment, UserDetails userDetails) {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        Optional<Post> postOptional = postRepository.findById(postId);

        if (userOptional.isEmpty() || postOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User or Post not found");
        }
        comment.setUser(userOptional.get());
        comment.setPost(postOptional.get());
        comment.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        commentRepository.save(comment);
        return null;
    }
}
