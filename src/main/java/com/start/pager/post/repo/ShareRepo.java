package com.start.pager.post.repo;

import com.start.pager.post.model.Share;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareRepo extends JpaRepository<Share, Long> {
    long countByPost_PostId(Long postId);

}