package com.OnlineMarket.controller;

import com.OnlineMarket.domain.User;
import com.OnlineMarket.security.SecurityUtils;
import com.OnlineMarket.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AccountResource {
    private final UserService userService;

    public AccountResource(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/account")
    public ResponseEntity<?> getAccount(){
        String login = SecurityUtils.getCurrentUserName().get();
        Optional<User> user = userService.findByUsername(login);
        return ResponseEntity.ok(user);
    }
}
