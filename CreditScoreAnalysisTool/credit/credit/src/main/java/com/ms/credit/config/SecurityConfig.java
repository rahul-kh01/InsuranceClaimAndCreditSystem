package com.ms.credit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ms.credit.filter.JwtAuthenticationFilter;

/**
 * Security configuration class that enables web security and configures HTTP security rules.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain that applies to all HTTP requests.
     * @param http HttpSecurity object provided by Spring Security to configure web based security.
     * @return the built SecurityFilterChain which holds the configuration of how security is managed.
     * @throws Exception if an error occurs during the configuration
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/score/**").authenticated()  // Protect all credit score endpoints
                .anyRequest().permitAll())  // Allow other requests (health checks, etc.)
            .oauth2Login(oauth2 -> oauth2
                .loginPage("/login"))
            .addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
