package com.start.pager.post.dto;

import com.start.pager.post.model.Post;
import com.start.pager.usersOnBoarding.dto.UserDto;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * DTO for {@link Post}
 */
public class PostDto implements Serializable {
    private final Long postId;
    private final UserDto user;
    private final String contentType;
    private final String textContent;
    private final String imageUrl;
    private final String videoUrl;
    private final String description;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;

    public PostDto(Long postId, UserDto user, String contentType, String textContent, String imageUrl, String videoUrl, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.postId = postId;
        this.user = user;
        this.contentType = contentType;
        this.textContent = textContent;
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getPostId() {
        return postId;
    }

    public UserDto getUser() {
        return user;
    }

    public String getContentType() {
        return contentType;
    }

    public String getTextContent() {
        return textContent;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getDescription() {
        return description;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostDto entity = (PostDto) o;
        return Objects.equals(this.postId, entity.postId) &&
                Objects.equals(this.user, entity.user) &&
                Objects.equals(this.contentType, entity.contentType) &&
                Objects.equals(this.textContent, entity.textContent) &&
                Objects.equals(this.imageUrl, entity.imageUrl) &&
                Objects.equals(this.videoUrl, entity.videoUrl) &&
                Objects.equals(this.description, entity.description) &&
                Objects.equals(this.createdAt, entity.createdAt) &&
                Objects.equals(this.updatedAt, entity.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, user, contentType, textContent, imageUrl, videoUrl, description, createdAt, updatedAt);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "postId = " + postId + ", " +
                "user = " + user + ", " +
                "contentType = " + contentType + ", " +
                "textContent = " + textContent + ", " +
                "imageUrl = " + imageUrl + ", " +
                "videoUrl = " + videoUrl + ", " +
                "description = " + description + ", " +
                "createdAt = " + createdAt + ", " +
                "updatedAt = " + updatedAt + ")";
    }
}