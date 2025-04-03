package com.start.pager.post.ctrl;


import com.start.pager.post.dto.PostDto;
import com.start.pager.post.model.*;
import com.start.pager.post.repo.*;
import com.start.pager.post.service.PostService;
import com.start.pager.usersOnBoarding.model.User;
import com.start.pager.usersOnBoarding.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class PostController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostRepo postRepository;

    @Autowired
    private LikeRepo likeRepository;

    @Autowired
    private CommentRepo commentRepository;

    @Autowired
    private ShareRepo shareRepository;

    @Autowired
    private UserFeedRepo userFeedRepository;


    @Autowired
    PostService postService;

    // ✅ Create Post
    @PostMapping("/posts")
    public ResponseEntity<?> createPost(@RequestBody PostDto post, @AuthenticationPrincipal UserDetails userDetails)  {
        ResponseEntity<String> errResponse = postService.createPostWithData(post, userDetails);
        if (errResponse != null) return errResponse;
        return ResponseEntity.ok("Post created successfully");
    }

    // ✅ Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.findAllPosts());
    }

    // ✅ Like/Unlike Post
    @PostMapping("/posts/{postId}/like")
    public ResponseEntity<?> likeOrUnlikePost(@PathVariable Long postId,@AuthenticationPrincipal UserDetails userDetails) {
        Optional<User> userOptional = userRepository.findByUsername(userDetails.getUsername());
        Optional<Post> postOptional = postRepository.findById(postId);

        if (userOptional.isEmpty() || postOptional.isEmpty()) {
            return ResponseEntity.badRequest().body("User or Post not found");
        }

        Like existingLike = likeRepository.findByUserAndPost(userOptional.get(), postOptional.get());
        if (existingLike != null) {
            likeRepository.delete(existingLike);
            return ResponseEntity.ok("Post unliked");
        } else {
            Like like = new Like();
            like.setUser(userOptional.get());
            like.setPost(postOptional.get());
            like.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            likeRepository.save(like);
            return ResponseEntity.ok("Post liked");
        }
    }

    // ✅ Comment on Post
    @PostMapping("/posts/{postId}/comment")
    public ResponseEntity<?> addComment(@PathVariable Long postId, @RequestBody Comment comment
    ,@AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity<String> response = postService.addComments(postId, comment, userDetails);
        if (response != null) return response;
        return ResponseEntity.ok("Comment added successfully");
    }



    // ✅ Share Post
    @PostMapping("/posts/{postId}/share")
    public ResponseEntity<?> sharePost(@PathVariable Long postId,
                                       @AuthenticationPrincipal UserDetails userDetails) {
        ResponseEntity<String> response = postService.shareThePost(postId, userDetails);
        if (response != null) return response;
        return ResponseEntity.ok("Post shared successfully");
    }



    // ✅ Get Comments for a Post
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable Long postId, @AuthenticationPrincipal UserDetails userDetails) {
        List<Comment> comments = postService.getComments(postId);
        return ResponseEntity.ok(comments);
    }



    // ✅ Get Likes Count for a Post
    @GetMapping("/posts/{postId}/likes")
    public ResponseEntity<Long> getLikeCount(@PathVariable Long postId) {
        Long likeCount = likeRepository.countByPost_PostId(postId);
        return ResponseEntity.ok(likeCount);
    }

    // ✅ Get Shares Count for a Post
    @GetMapping("/posts/{postId}/shares")
    public ResponseEntity<Long> getShareCount(@PathVariable Long postId) {
        Long shareCount = shareRepository.countByPost_PostId(postId);
        return ResponseEntity.ok(shareCount);
    }

    // ✅ Get User Feed
    @GetMapping("/users/{userId}/feed")
    public ResponseEntity<List<UserFeed>> getUserFeed(@PathVariable Long userId) {
        List<UserFeed> feeds = userFeedRepository.findByUser_Id(userId);
        return ResponseEntity.ok(feeds);
    }
}
