package com.OnlineMarket.domain.dto;

import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.location.Region;
import org.springframework.web.multipart.MultipartFile;

public class ProductRequest {
        private String name;
        private String description;
        private double price;
        private MultipartFile image;
        private Region region;
        private String district;
        private BigCategory bigCategory;
        private String category;


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

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
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
