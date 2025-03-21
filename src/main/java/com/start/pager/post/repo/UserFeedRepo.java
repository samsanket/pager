package com.start.pager.post.repo;

import com.start.pager.post.model.UserFeed;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserFeedRepo extends JpaRepository<UserFeed, Long> {
    List<UserFeed> findByUser_Id(Long id);
}