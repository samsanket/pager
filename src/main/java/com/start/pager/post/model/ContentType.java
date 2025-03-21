package com.start.pager.post.model;

public  enum ContentType {
    TEXT,
    IMAGE,
    VIDEO;

    public static ContentType valueOfIgnoreCase(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Content type cannot be null or empty");
        }
        for (ContentType contentType : ContentType.values()) {
            if (contentType.name().equalsIgnoreCase(type.trim())) {
                return contentType;
            }
        }
        throw new IllegalArgumentException("Invalid content type: " + type);
    }

    public static ContentType fromString(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Content type cannot be null");
        }
        try {
            return ContentType.valueOf(type.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid content type: " + type);
        }
    }

}
