package com.ashaari.hooman.expensetracker.config;

import com.ashaari.hooman.expensetracker.business.security.service.impl.ExpenseTrackerUserDetailsService;
import com.ashaari.hooman.expensetracker.filter.JwtAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.ashaari.hooman.expensetracker.api.restcontroller.util.RestUtils.USERS_API_V_1;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final ExpenseTrackerUserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Used  for  user authentication. We are going
     * to use a custom UserDetailsService to do so.
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   AuthenticationManager authenticationManager) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable) // Because we are going to be stateless
                .cors(AbstractHttpConfigurer::disable) // Because we are going to be stateless
                .authorizeHttpRequests(authorizeHttpRequest -> authorizeHttpRequest
                        // public endpoints
                        .requestMatchers(HttpMethod.POST, USERS_API_V_1 + "/sign-up/**").permitAll()
                        .requestMatchers(HttpMethod.POST, USERS_API_V_1 + "/login/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/swagger-ui/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/openapi/**").permitAll()
                        // private endpoints
                        .anyRequest().authenticated())
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationManager(authenticationManager)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}
