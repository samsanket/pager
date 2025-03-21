package com.start.pager.post.dto;

import com.start.pager.usersOnBoarding.dto.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * DTO for {@link com.start.pager.post.model.Like}
 */
public class LikeDto implements Serializable {
    private final Long likeId;
    private final UserDto user;
    private final PostDto post;
    private final Timestamp createdAt;

    public LikeDto(Long likeId, UserDto user, PostDto post, Timestamp createdAt) {
        this.likeId = likeId;
        this.user = user;
        this.post = post;
        this.createdAt = createdAt;
    }

    public Long getLikeId() {
        return likeId;
    }

    public UserDto getUser() {
        return user;
    }

    public PostDto getPost() {
        return post;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikeDto entity = (LikeDto) o;
        return Objects.equals(this.likeId, entity.likeId) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.post, entity.post) &&
                Objects.equals(this.createdAt, entity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(likeId, user, post, createdAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "likeId = " + likeId + ", " +
                "user = " + user + ", " +
                "post = " + post + ", " +
                "createdAt = " + createdAt + ")";
    }
}