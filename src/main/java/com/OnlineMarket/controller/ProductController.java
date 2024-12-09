package com.OnlineMarket.controller;

import com.OnlineMarket.domain.Comment;
import com.OnlineMarket.domain.Product;
import com.OnlineMarket.domain.User;
import com.OnlineMarket.domain.dto.*;
import com.OnlineMarket.domain.status.category.BigCategory;
import com.OnlineMarket.domain.status.category.CategoryValidator;
import com.OnlineMarket.domain.status.location.DistrictValidator;
import com.OnlineMarket.domain.status.location.Region;
import com.OnlineMarket.repository.CommentRepository;
import com.OnlineMarket.repository.ProductRepository;
import com.OnlineMarket.repository.UserRepository;
import com.OnlineMarket.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;



    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@ModelAttribute ProductRequest productRequest) {

        try {
            // Foydalanuvchini olish
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User user = userRepository.findByUsername(username).orElse(null);

            if (user == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
            }

            Region region = productRequest.getRegion();
            String district = productRequest.getDistrict();

            if (!DistrictValidator.isDistrictValid(region, district)) {
                return ResponseEntity.badRequest().body(null); // Xato: tuman yoki viloyat noto'g'ri
            }

            BigCategory bigCategory = productRequest.getBigCategory();
            String category = productRequest.getCategory();

            if (!CategoryValidator.isCategoryValid(bigCategory, category)) {
                return ResponseEntity.badRequest().body(null); //Xato: Category
            }


            // Faylni saqlash
            String fileName = StringUtils.cleanPath(productRequest.getImage().getOriginalFilename());
            String uploadDir = "img/";
            Path filePath = Paths.get(uploadDir + fileName);
            Files.copy(productRequest.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Mahsulot yaratish
            Product product = new Product();
            product.setName(productRequest.getName());
            product.setDescription(productRequest.getDescription());
            product.setPrice(productRequest.getPrice());
            product.setAddedBy(user);
            product.setAddedAt(LocalDateTime.now());
            product.setImageUrl(uploadDir + fileName);
            product.setRegion(region);
            product.setDistrict(district);
            product.setBigCategory(bigCategory);
            product.setCategory(category);

            productRepository.save(product);

            // ProductResponse ni to'ldirish
            ProductResponse response = new ProductResponse();
            response.setName(product.getName());
            response.setDescription(product.getDescription());
            response.setPrice(product.getPrice());
            response.setImageUrl(product.getImageUrl());
            response.setAddedByFirstName(user.getFirstName());  // Faqat firstName
            response.setAddedByLastName(user.getLastName());    // Faqat lastName
            response.setRegion(region);
            response.setDistrict(district);
            response.setBigCategory(bigCategory);
            response.setCategory(category);

            return ResponseEntity.ok(response);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @PutMapping("/edit")
    public ResponseEntity<?> editProduct(@ModelAttribute ProductEditRequest productEditRequest) {
        // Joriy foydalanuvchini olish
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User currentUser = userRepository.findByUsername(currentUsername).orElse(null);

        if (currentUser == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Foydalanuvchi aniqlanmadi!");
        }

        // Mahsulotni olish
        Product product = productRepository.findById(productEditRequest.getProductId())
                .orElse(null);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Mahsulot topilmadi!");
        }

        // Foydalanuvchini tekshirish
        if (!product.getAddedBy().getId().equals(currentUser.getId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu mahsulotni tahrir qilishga ruxsatingiz yo'q!");
        }

        try {
            // Faylni saqlash (agar rasm yuborilgan bo'lsa)
            if (productEditRequest.getImage() != null && !productEditRequest.getImage().isEmpty()) {
                String fileName = StringUtils.cleanPath(productEditRequest.getImage().getOriginalFilename());
                String uploadDir = "img/";
                Path filePath = Paths.get(uploadDir + fileName);
                Files.copy(productEditRequest.getImage().getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                product.setImageUrl(uploadDir + fileName);
            }

            // Mahsulotni yangilash
            product.setName(productEditRequest.getName());
            product.setDescription(productEditRequest.getDescription());
            product.setPrice(productEditRequest.getPrice());
            product.setRegion(productEditRequest.getRegion());
            product.setDistrict(productEditRequest.getDistrict());
            product.setBigCategory(productEditRequest.getBigCategory());
            product.setCategory(productEditRequest.getCategory());

            productRepository.save(product);

            return ResponseEntity.ok("Mahsulot muvaffaqiyatli yangilandi!");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Rasmni saqlashda xatolik yuz berdi.");
        }
    }



    @PostMapping("/{productId}/comments")
    public ResponseEntity<?> addComment(@PathVariable Long productId, @RequestBody String content) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Foydalanuvchi topilmadi"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mahsulot topilmadi"));

        Comment comment = new Comment();
        comment.setContent(content);
        comment.setProduct(product);
        comment.setUser(user);
        comment.setCreatedAt(LocalDateTime.now());
        commentRepository.save(comment);

        return ResponseEntity.ok("Sharh muvaffaqiyatli qo'shildi");
    }



    @GetMapping("/get/all")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<Product> products = productRepository.findAllByOrderByProductIdAsc();
        List<ProductDto> productDtos = products.stream()
                .map(this::convertToDto)  // Mapping Product to ProductDto
                .collect(Collectors.toList());

        return ResponseEntity.ok(productDtos);
    }


    private ProductDto convertToDto(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getRegion(),
                product.getDistrict(),
                product.getBigCategory(),
                product.getCategory(),
                product.getAddedByFirstName(), // Product entity ichidagi yordamchi metod
                product.getAddedByLastName(),  // Product entity ichidagi yordamchi metod
                product.getAddedAt()

        );
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Mahsulot topilmadi"));

        // Mahsulot sharhlarini olish
        List<Comment> comments = commentRepository.findByProduct(product);
        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> new CommentDto(comment.getContent(), comment.getUser().getUsername(), comment.getCreatedAt()))
                .collect(Collectors.toList());

        ProductDto productDto = convertToDto(product);
        productDto.setComments(commentDtos); // Sharhlarni qo'shish

        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/user/comments")
    public ResponseEntity<List<UserCommentResponse>> getUserComments(Authentication authentication) {
        // Tizimga kirgan foydalanuvchi username-sini olish
        String currentUsername = authentication.getName();

        // Foydalanuvchini username orqali topish
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));

        // Foydalanuvchiga tegishli sharhlarni olish
        List<Comment> comments = commentRepository.findByUser(user);

        // Sharhlarni DTO ga o'zgartirish
        List<UserCommentResponse> responses = comments.stream()
                .map(this::convertToUserCommentResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    private UserCommentResponse convertToUserCommentResponse(Comment comment) {
        return new UserCommentResponse(
                comment.getContent(),
                comment.getCreatedAt(),
                comment.getProduct().getProductId(),
                comment.getProduct().getName(), // Sharh qaysi productga tegishli
                comment.getProduct().getDescription()

        );
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long productId) {
        // Tizimga kirgan foydalanuvchini olish
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Foydalanuvchi topilmadi!"));

        // Mahsulotni bazadan olish
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Mahsulot topilmadi!"));

        // Foydalanuvchini tekshirish
        if (!product.getAddedBy().getUsername().equals(currentUser.getUsername())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Bu mahsulotni o'chirishga ruxsatingiz yo'q!");
        }

        // Mahsulotni o‘chirish
        productRepository.delete(product);
        return ResponseEntity.ok("Mahsulot muvaffaqiyatli o'chirildi!");
    }

    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();

        boolean isDeleted = commentService.deleteComment(commentId, currentUsername);

        if (isDeleted) {
            return ResponseEntity.ok("Sharh muvaffaqiyatli o'chirildi!");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Siz faqat o'zingizning sharhingizni o'chira olasiz!");
        }
    }
}


