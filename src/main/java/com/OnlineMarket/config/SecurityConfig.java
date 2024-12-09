package com.OnlineMarket.config;

import com.OnlineMarket.security.JWTConfigurer;
import com.OnlineMarket.security.TokenProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final TokenProvider tokenProvider;

    public SecurityConfig(TokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests()
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").permitAll()
                .requestMatchers("api/login").permitAll()
                .requestMatchers("api/market/register").permitAll()
                .requestMatchers("api/assign-admin").permitAll()
                .requestMatchers("/api/products/add").permitAll()
                .requestMatchers("/api/products/get/all").permitAll()
                .requestMatchers("api/market/get/all").permitAll()
                .requestMatchers("api/account").permitAll()
                .requestMatchers("api/admin/assign-role").permitAll()
                .requestMatchers("api/delete/account/{id}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .httpBasic()
                .and()
                .apply(configurer());
        return http.build();
    }
    private JWTConfigurer configurer(){
        return new JWTConfigurer(tokenProvider);
    }
}
