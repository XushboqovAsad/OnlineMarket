package com.OnlineMarket.domain;

import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.location.Region;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    private String name;

    private String description;

    private double price;

    private String imageUrl;

    private Region region;

    private String district;

    private BigCategory bigCategory;

    private String category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "added_by_user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private User addedBy;

    public String getAddedByFirstName() {
        return this.addedBy != null ? this.addedBy.getFirstName() : "Unknown";
    }

    public String getAddedByLastName() {
        return this.addedBy != null ? this.addedBy.getLastName() : "Unknown";
    }

    @Column(name = "added_at", nullable = false)
    private LocalDateTime addedAt;  // Mahsulot qo'shilgan vaqt

    public Product() {}

    public Product(String name, String description, double price, User addedBy, LocalDateTime addedAt) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.addedBy = addedBy;
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

    public User getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(User addedBy) {
        this.addedBy = addedBy;
    }

    public LocalDateTime getAddedAt() {
        return addedAt;
    }

    public void setAddedAt(LocalDateTime addedAt) {
        this.addedAt = addedAt;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
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
}
