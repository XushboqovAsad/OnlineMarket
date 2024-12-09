package com.OnlineMarket.domain.status.category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @GetMapping("/get-category")
    public ResponseEntity<List<String>> getCategoryByBigCategory(@RequestParam BigCategory bigCategory) {
        List<String> categories = CategoryValidator.getCategoryByBigCategory(bigCategory);
        return ResponseEntity.ok(categories);
    }
}
