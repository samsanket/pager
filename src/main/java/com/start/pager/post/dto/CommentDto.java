package com.start.pager.post.dto;

import com.start.pager.post.model.Comment;
import com.start.pager.usersOnBoarding.dto.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * DTO for {@link Comment}
 */
public class CommentDto implements Serializable {
    private final Long commentId;
    private final UserDto user;
    private final PostDto post;
    private final String commentText;
    private final Timestamp createdAt;

    public CommentDto(Long commentId, UserDto user, PostDto post, String commentText, Timestamp createdAt) {
        this.commentId = commentId;
        this.user = user;
        this.post = post;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }

    public Long getCommentId() {
        return commentId;
    }

    public UserDto getUser() {
        return user;
    }

    public PostDto getPost() {
        return post;
    }

    public String getCommentText() {
        return commentText;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentDto entity = (CommentDto) o;
        return Objects.equals(this.commentId, entity.commentId) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.post, entity.post) &&
                Objects.equals(this.commentText, entity.commentText) &&
                Objects.equals(this.createdAt, entity.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, user, post, commentText, createdAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "commentId = " + commentId + ", " +
                "user = " + user + ", " +
                "post = " + post + ", " +
                "commentText = " + commentText + ", " +
                "createdAt = " + createdAt + ")";
    }
}