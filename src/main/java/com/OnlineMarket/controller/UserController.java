package com.OnlineMarket.controller;

import com.OnlineMarket.domain.User;
import com.OnlineMarket.domain.status.RoleStatus;
import com.OnlineMarket.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/market/register")
    public ResponseEntity addUser(@Valid @RequestBody User user) {

        if (userService.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("username is already taken");
        }

        if (userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("email is already signed up");
        }



        if (user.getFirstName() == null || user.getFirstName().trim().isEmpty()){
            return ResponseEntity.badRequest().body("first name is required");
        }
        if (user.getLastName() == null || user.getLastName().trim().isEmpty()){
            return ResponseEntity.badRequest().body("last name is required");
        }
        if (user.getEmail() == null || user.getEmail().trim().isEmpty()){
            return ResponseEntity.badRequest().body("email is required");
        }
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()){
            return ResponseEntity.badRequest().body("username is required");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()){
            return ResponseEntity.badRequest().body("password is required");
        }

        if (user.getRoleStatus() == null) {
            user.setRoleStatus(RoleStatus.USER);
        }

        User result = userService.register(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign-admin")
    public ResponseEntity<String> assignAdminRole(@RequestParam String username) {
        userService.assignAdminRole(username);
        return ResponseEntity.ok("User is now an admin.");
    }

    @GetMapping("/market/get/username")
    public ResponseEntity getUsername (@RequestBody String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @GetMapping("/market/get/all")
    public ResponseEntity getAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @DeleteMapping("/delete/user/{id}")
    public ResponseEntity deleteUser(@PathVariable Long id) {
        userService.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
