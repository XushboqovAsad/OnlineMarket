package com.OnlineMarket.domain.dto;

import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.location.Region;

public class ProductResponse {
    private String name;
    private String description;
    private Double price;
    private String imageUrl;
    private String addedByFirstName;     //user's firstName
    private String addedByLastName;      //user's lastName
    private Region region;
    private String district;
    private BigCategory bigCategory;
    private String category;


    // Getter va Setterlar

    public ProductResponse() {

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
