package com.hotel.config;

import com.hotel.service.impl.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// Annotation to enable Spring Security configurations
@EnableWebSecurity
// Class defining default security configurations
public class DefaultSecurityConfig {

    // Autowired instance of CustomAuthenticationProvider
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    // Bean definition for default security filter chain
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // Configure authorization for HTTP requests
        http.authorizeHttpRequests(authorizeRequests ->
                // Specify any request should be authenticated
                authorizeRequests.anyRequest().authenticated()
        );
        // Build and return the security filter chain
        return http.build();
    }

    // Method to bind custom authentication provider to AuthenticationManagerBuilder
    @Autowired
    public void bindAuthenticationProvider(AuthenticationManagerBuilder authenticationManagerBuilder) {
        // Add custom authentication provider to authentication manager builder
        authenticationManagerBuilder
                .authenticationProvider(customAuthenticationProvider);
    }
}
