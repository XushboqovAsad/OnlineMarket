package com.OnlineMarket.controller;

import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.dto.ProductDto;
import com.OnlineMarket.service.FavoriteService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/favorites")
public class FavoriteController {

    private final FavoriteService favoriteService;

    public FavoriteController(FavoriteService favoriteService) {
        this.favoriteService = favoriteService;
    }

    @PostMapping("/add/{productId}")
    public ResponseEntity<?> addToFavorites(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        favoriteService.addToFavorites(productId, username);
        return ResponseEntity.ok("Mahsulot saralanganlarga qo'shildi!");
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeFromFavorites(@PathVariable Long productId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        favoriteService.removeFromFavorites(productId, username);
        return ResponseEntity.ok("Mahsulot saralanganlardan o'chirildi!");
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductDto>> getFavorites() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Product> favorites = favoriteService.getFavorites(username);
        List<ProductDto> favoriteDtos = favorites.stream()
                .map(product -> new ProductDto(
                        product.getProductId(),
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        product.getImageUrl(),
                        product.getRegion(),
                        product.getDistrict(),
                        product.getBigCategory(),
                        product.getCategory(),
                        product.getAddedByFirstName(),
                        product.getAddedByLastName(),
                        product.getAddedAt()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(favoriteDtos);
    }
}

