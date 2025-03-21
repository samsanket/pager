package com.start.pager.post.repo;

import com.start.pager.post.model.Comment;
import com.start.pager.post.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepo extends JpaRepository<Comment, Long> {
    Comment findByPost(Post post);

    List<Comment> findByPost_PostId(Long postId);


}