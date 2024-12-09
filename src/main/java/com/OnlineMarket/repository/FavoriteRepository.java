package com.OnlineMarket.repository;

import com.OnlineMarket.domain.Favorite;
import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {

    List<Favorite> findByUser(User user);

    Optional<Favorite> findByUserAndProduct(User user, Product product);
}

