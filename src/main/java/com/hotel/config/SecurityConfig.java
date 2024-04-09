package com.hotel.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

// Configuration class for security configurations
@Configuration
// Annotation to enable Spring Security configurations
@EnableWebSecurity
// Annotation to enable method-level security
@EnableMethodSecurity
public class SecurityConfig {

    // Array of URLs to whitelist
    private static final String[] WHITE_LIST_URLS = {
            "/", "/authenticate"
    };

    // UserDetailsService instance for user details retrieval
    private UserDetailsService userDetailsService;

    // Constructor injecting UserDetailsService
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Bean definition for AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    // Bean definition for SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Configure CSRF protection
        http.csrf(csrf -> csrf.disable())
                // Configure authorization for HTTP requests
                .authorizeHttpRequests((authorize) ->
                        // Whitelist specific URLs
                        authorize.requestMatchers(WHITE_LIST_URLS).permitAll()
//                                .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                                // All other requests require authentication
                                .anyRequest().authenticated()
                )
                .cors(Customizer.withDefaults())
                // Configure HTTP Basic authentication
                .httpBasic(withDefaults());

        // Build and return the security filter chain
        return http.build();
    }

}
