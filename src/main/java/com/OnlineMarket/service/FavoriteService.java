package com.OnlineMarket.service;

import com.OnlineMarket.domain.Favorite;
import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.User;
import com.OnlineMarket.repository.FavoriteRepository;
import com.OnlineMarket.repository.ProductRepository;
import com.OnlineMarket.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteService {

    private final FavoriteRepository favoriteRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public FavoriteService(FavoriteRepository favoriteRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.favoriteRepository = favoriteRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    public void addToFavorites(Long productId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi"));

        if (favoriteRepository.findByUserAndProduct(user, product).isPresent()) {
            throw new RuntimeException("Bu mahsulot allaqachon saralangan!");
        }

        Favorite favorite = new Favorite();
        favorite.setUser(user);
        favorite.setProduct(product);
        favorite.setAddedAt(LocalDateTime.now());
        favoriteRepository.save(favorite);
    }

    public void removeFromFavorites(Long productId, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi"));

        Favorite favorite = favoriteRepository.findByUserAndProduct(user, product)
                .orElseThrow(() -> new RuntimeException("Saralangan mahsulot topilmadi!"));
        favoriteRepository.delete(favorite);
    }

    public List<Product> getFavorites(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi"));
        List<Favorite> favorites = favoriteRepository.findByUser(user);
        return favorites.stream().map(Favorite::getProduct).collect(Collectors.toList());
    }
}

