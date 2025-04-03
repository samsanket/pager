package com.start.pager.post.model;

import com.start.pager.usersOnBoarding.model.User;
import jakarta.persistence.*;

import java.sql.Timestamp;


@Entity
@Table(name = "likes", uniqueConstraints = @UniqueConstraint(columnNames = {"id", "post_id"}))
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = new Timestamp(System.currentTimeMillis());
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
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

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
