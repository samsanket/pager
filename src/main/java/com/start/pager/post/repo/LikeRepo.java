package com.start.pager.post.repo;

import com.start.pager.post.model.Like;
import com.start.pager.post.model.Post;
import com.start.pager.usersOnBoarding.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepo extends JpaRepository<Like, Long> {
    long countByPost_PostId(Long postId);

    Like findByUserAndPost(User user, Post post);


}