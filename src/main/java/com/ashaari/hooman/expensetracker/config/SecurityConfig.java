package com.ashaari.hooman.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.USERS_API_V_1;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Because we are going to be stateless
                .authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        // public endpoints
                        .requestMatchers(HttpMethod.POST, USERS_API_V_1 + "/sign-up/**").permitAll()
                        .requestMatchers(HttpMethod.POST, USERS_API_V_1 + "/login/**").permitAll()
                        // private endpoints
                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }



}
