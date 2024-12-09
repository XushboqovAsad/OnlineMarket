package com.OnlineMarket.domain.dto;

import java.time.LocalDateTime;

public class CommentDto {
    private String content;
    private String username; // Sharhni yozgan foydalanuvchi
    private LocalDateTime createdAt;

    public CommentDto(String content, String username, LocalDateTime createdAt) {
        this.content = content;
        this.username = username;
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
