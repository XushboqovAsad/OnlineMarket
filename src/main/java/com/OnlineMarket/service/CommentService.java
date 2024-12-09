package com.OnlineMarket.service;

import com.OnlineMarket.domain.Comment;
import com.OnlineMarket.repository.CommentRepository;
import com.OnlineMarket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public boolean deleteComment(Long commentId, String currentUsername) {
        // Sharhni topish
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Sharh topilmadi!"));

        // Sharhni kim yozganini tekshirish
        if (comment.getUser().getUsername().equals(currentUsername)) {
            // Sharhni o'chirish
            commentRepository.delete(comment);
            return true;
        } else {
            throw new RuntimeException("Siz faqat o'zingizning sharhingizni o'chira olasiz!");
        }
    }
}
