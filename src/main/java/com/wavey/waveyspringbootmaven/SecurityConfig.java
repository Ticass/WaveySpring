package com.wavey.waveyspringbootmaven;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
// Removed the import for AntPathRequestMatcher as it's no longer explicitly needed

@Configuration
@EnableWebSecurity // Enables Spring Security's web security support
public class SecurityConfig {

    // This bean provides the BCryptPasswordEncoder for hashing passwords
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This bean defines the security filter chain, customizing security rules
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable) // Disable CSRF for simplicity in development (enable in production!)
                .authorizeHttpRequests(authorize -> authorize
                        // Allow unauthenticated access to the user registration endpoint
                        // Directly pass the pattern as a string
                        .requestMatchers("/api/users/register").permitAll()
                        .requestMatchers("/api/users/login").permitAll()
                        // Allow unauthenticated access to retrieve all waves
                        // Directly pass the pattern as a string
                        .requestMatchers("/api/waves/getWaves").permitAll()
                        .requestMatchers("/api/waves/create").permitAll()
                        // You can add more public endpoints here, e.g.,
                        // .requestMatchers("/api/public/**").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        // Stateless session management, typical for REST APIs (no session cookies)
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        // .httpBasic(withDefaults()); // You can enable basic authentication if needed for other protected endpoints

        return http.build();
    }
}