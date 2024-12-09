package com.OnlineMarket.domain.dto;

import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.location.Region;

import java.time.LocalDateTime;
import java.util.List;

public class ProductDto {
    private Long productId;
    private String name;
    private String description;
    private double price;
    private String imageUrl;
    private Region region;
    private String district;
    private BigCategory bigCategory;
    private String category;
    private String addedByFirstName;
    private String addedByLastName;
    private LocalDateTime addedAt;
    private List<CommentDto> comments;



    public ProductDto(Long productId, String name, String description, double price, String imageUrl,
                      Region region, String district, BigCategory bigCategory, String category,
                      String addedByFirstName, String addedByLastName, LocalDateTime addedAt) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.bigCategory = bigCategory;
        this.category = category;
        this.region = region;
        this.district = district;
        this.addedByFirstName = addedByFirstName;
        this.addedByLastName = addedByLastName;
        this.addedAt = addedAt;
    }



    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAddedByFirstName() {
        return addedByFirstName;
    }

    public void setAddedByFirstName(String addedByFirstName) {
        this.addedByFirstName = addedByFirstName;
    }

    public String getAddedByLastName() {
        return addedByLastName;
    }

    public void setAddedByLastName(String addedByLastName) {
        this.addedByLastName = addedByLastName;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public BigCategory getBigCategory() {
        return bigCategory;
    }

    public void setBigCategory(BigCategory bigCategory) {
        this.bigCategory = bigCategory;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public void setComments(List<CommentDto> comments) {
        this.comments = comments;
    }
}
