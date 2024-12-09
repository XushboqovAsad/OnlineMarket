package com.OnlineMarket.repository;

import com.OnlineMarket.domain.Comment;
import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProduct(Product product);
    List<Comment> findByUser(User user);
}
