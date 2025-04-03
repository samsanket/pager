package com.start.pager.post.model;

import com.start.pager.usersOnBoarding.model.User;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "user_feed")
public class UserFeed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "is_shared", nullable = false)
    private Boolean isShared;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getFeedId() {
        return feedId;
    }

    public void setFeedId(Long feedId) {
        this.feedId = feedId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Boolean getShared() {
        return isShared;
    }

    public void setShared(Boolean shared) {
        isShared = shared;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
