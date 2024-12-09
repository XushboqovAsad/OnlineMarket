package com.OnlineMarket.domain.dto;

import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.location.Region;
import org.springframework.web.multipart.MultipartFile;


public class ProductEditRequest {
    private Long productId;             // Mahsulot ID (majburiy)
    private String name;                // Mahsulot nomi (majburiy)
    private String description;         // Mahsulot tavsifi
    private Double price;               // Mahsulot narxi
    private MultipartFile image;        // Yangilangan rasm (ixtiyoriy)
    private Region region;              // Mahsulot joylashuvi (viloyat)
    private String district;            // Mahsulot joylashuvi (tuman)
    private BigCategory bigCategory;    // Asosiy kategoriya
    private String category;            // Kichik kategoriya

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
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
