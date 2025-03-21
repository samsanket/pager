package com.start.pager.post.dto;

import com.start.pager.usersOnBoarding.dto.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * DTO for {@link com.start.pager.post.model.Share}
 */
public class ShareDto implements Serializable {
    private final Long shareId;
    private final UserDto user;
    private final PostDto post;
    private final Timestamp sharedAt;

    public ShareDto(Long shareId, UserDto user, PostDto post, Timestamp sharedAt) {
        this.shareId = shareId;
        this.user = user;
        this.post = post;
        this.sharedAt = sharedAt;
    }

    public Long getShareId() {
        return shareId;
    }

    public UserDto getUser() {
        return user;
    }

    public PostDto getPost() {
        return post;
    }

    public Timestamp getSharedAt() {
        return sharedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShareDto entity = (ShareDto) o;
        return Objects.equals(this.shareId, entity.shareId) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.post, entity.post) &&
                Objects.equals(this.sharedAt, entity.sharedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shareId, user, post, sharedAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "shareId = " + shareId + ", " +
                "user = " + user + ", " +
                "post = " + post + ", " +
                "sharedAt = " + sharedAt + ")";
    }
}