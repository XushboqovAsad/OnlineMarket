package com.OnlineMarket.domain.dto;

import java.time.LocalDateTime;

public class UserCommentResponse {
    private String commentContent;
    private LocalDateTime commentCreatedAt;
    private Long productId;
    private String productName;
    private String productDescription;


    public UserCommentResponse(String commentContent, LocalDateTime commentCreatedAt, Long productId, String productName, String productDescription) {
        this.commentContent = commentContent;
        this.commentCreatedAt = commentCreatedAt;
        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;

    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public LocalDateTime getCommentCreatedAt() {
        return commentCreatedAt;
    }

    public void setCommentCreatedAt(LocalDateTime commentCreatedAt) {
        this.commentCreatedAt = commentCreatedAt;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }
}
